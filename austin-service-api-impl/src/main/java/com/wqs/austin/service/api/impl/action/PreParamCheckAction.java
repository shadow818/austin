package com.wqs.austin.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.wqs.austin.common.enums.RespStatusEnum;
import com.wqs.austin.common.vo.BasicResultVO;
import com.wqs.austin.service.api.domain.MessageParam;
import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.support.pipeline.BusinessProcess;
import com.wqs.austin.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前置参数校验
 * author: wqs
 * date: 2022/10/15 18:21
 */
@Slf4j
@Service
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {

    //最大的人数
    private static final Integer BATCH_RECEIVER_SIZE = 100;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        //消息模板对象
        SendTaskModel sendTaskModel = context.getProcessModel();
        //模板ID
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        //模板里面的参数
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        // 1. 没有传入 消息模板ID 或者 messageParam
        if (messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 2. 过滤消息接收者为空的 messageParam
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(resultMessageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        //3. 过滤receiver 大于 100 的请求
        if (messageParamList.stream().anyMatch(messageParam -> messageParam.getReceiver().split(StrUtil.COMMA).length > BATCH_RECEIVER_SIZE)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TOO_MANY_RECEIVER));
            return;
        }

    }
}
