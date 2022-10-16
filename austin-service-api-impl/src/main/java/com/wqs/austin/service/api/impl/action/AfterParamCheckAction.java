package com.wqs.austin.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.common.enums.IdType;
import com.wqs.austin.common.enums.RespStatusEnum;
import com.wqs.austin.common.vo.BasicResultVO;
import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.support.pipeline.BusinessProcess;
import com.wqs.austin.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后置参数检查
 * author: wqs
 * date: 2022/10/16 20:47
 */
@Slf4j
@Service
public class AfterParamCheckAction implements BusinessProcess<SendTaskModel> {

    //电话号码和邮件格式正则化匹配
    public static final String PHONE_REGEX_EXP = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$";
    public static final String EMAIL_REGEX_EXP = "^[A-Za-z0-9-_\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    public static final HashMap<Integer, String> CHANNEL_REGEX_EXP = new HashMap<>();

    static {
        CHANNEL_REGEX_EXP.put(IdType.PHONE.getCode(), PHONE_REGEX_EXP);
        CHANNEL_REGEX_EXP.put(IdType.EMAIL.getCode(), EMAIL_REGEX_EXP);
    }

    /**
     * 如果指定类型是手机号，检测输入手机号是否合法
     * 如果指定类型是邮件，检测输入邮件是否合法
     * @param taskInfo 任务信息
     */
    private void filterIllegalReceiver(List<TaskInfo> taskInfo){
        Integer idType = CollUtil.getFirst(taskInfo.iterator()).getIdType();
        filter(taskInfo, CHANNEL_REGEX_EXP.get(idType));
    }

    /**
     * 利用正则过滤掉不合法的接收者
     * @param taskInfo 任务信息
     * @param regexExp 正则规则
     */
    private void filter(List<TaskInfo> taskInfo, String regexExp) {
        Iterator<TaskInfo> iterator = taskInfo.iterator();
        while (iterator.hasNext()){
            TaskInfo task = iterator.next();
            //获取到非法的电话号码
            Set<String> illegalPhone = task.getReceiver().stream()
                    .filter(phone -> !ReUtil.isMatch(regexExp, phone))
                    .collect(Collectors.toSet());
            //如果有非法的电话号码则删除，并打印日志
            if(CollUtil.isNotEmpty(illegalPhone)){
                task.getReceiver().removeAll(illegalPhone);
                log.error("messageTemplateId:{} find illegal receiver!{}", task.getMessageTemplateId(), JSON.toJSONString(illegalPhone));
            }
        }
    }

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();
        //过滤掉不合法的手机号和邮件
        filterIllegalReceiver(taskInfo);

        //如果消息模板内的电话号码或者邮件全都不合法，那么就中断消息发送，并返回客户端参数错误响应
        if(CollUtil.isNotEmpty(taskInfo)){
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
        }
    }
}
