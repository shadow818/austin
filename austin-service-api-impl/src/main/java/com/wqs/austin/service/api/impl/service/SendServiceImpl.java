package com.wqs.austin.service.api.impl.service;

import cn.monitor4all.logRecord.annotation.OperationLog;
import com.wqs.austin.common.vo.BasicResultVO;
import com.wqs.austin.service.api.domain.BatchSendRequest;
import com.wqs.austin.service.api.domain.SendRequest;
import com.wqs.austin.service.api.domain.SendResponse;
import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.service.api.service.SendService;
import com.wqs.austin.support.pipeline.ProcessContext;
import com.wqs.austin.support.pipeline.ProcessController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 消息发送接口实现
 * author: wqs
 * date: 2022/10/15 17:13
 */
@Service
public class SendServiceImpl implements SendService {

    //引入流程控制器
    @Autowired
    private ProcessController processController;

    @Override
    @OperationLog(bizType = "SendService#send", bizId = "#sendRequest.messageTemplateId", msg = "#sendRequest")
    public SendResponse send(SendRequest sendRequest) {
        //定义消息的模板
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        //构造消息的上下文
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success())
                .build();
        //使用流程控制器去处理上下文
        ProcessContext process = processController.process(context);
        //返回响应体，包含响应状态以及响应消息
        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }

    @Override
    @OperationLog(bizType = "SendService#batchSend", bizId = "#batchSendRequest.messageTemplateId", msg = "#batchSendRequest")
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        //定义消息的模板
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();
        //定义消息的上下文
        ProcessContext context = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success())
                .build();
        //使用流程控制器对消息进行发送处理
        ProcessContext process = processController.process(context);
        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }
}
