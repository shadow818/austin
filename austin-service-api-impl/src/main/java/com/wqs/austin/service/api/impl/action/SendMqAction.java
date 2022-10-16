package com.wqs.austin.service.api.impl.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.wqs.austin.common.enums.RespStatusEnum;
import com.wqs.austin.common.vo.BasicResultVO;
import com.wqs.austin.service.api.enums.BusinessCode;
import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.support.mq.SendMqService;
import com.wqs.austin.support.pipeline.BusinessProcess;
import com.wqs.austin.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 将消息发送到MQ
 * author: wqs
 * date: 2022/10/16 22:22
 */
@Slf4j
@Service
public class SendMqAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    private SendMqService sendMqService;

    // 发送topic名称
    @Value("${austin.business.topic.name}")
    private String sendMessageTopic;

    // 撤回topic消息
    @Value("${austin.business.recall.topic.name}")
    private String austinRecall;

    @Value("${austin.business.tagId.value}")
    private String tagId;

    @Value("${austin.mq.pipeline}")
    private String mqPipeline;


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        try {
            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                //发送消息
                String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(sendMessageTopic, message);
            } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
                //撤回消息
                String message = JSON.toJSONString(sendTaskModel.getMessageTemplate(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(austinRecall, message, tagId);
            }
        } catch (Exception e) {
            //服务错误
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send {} fail! e:{},params:{}", mqPipeline, Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }
    }
}
