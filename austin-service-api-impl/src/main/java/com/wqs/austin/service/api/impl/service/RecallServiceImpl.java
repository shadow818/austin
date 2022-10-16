package com.wqs.austin.service.api.impl.service;

import com.wqs.austin.common.vo.BasicResultVO;
import com.wqs.austin.service.api.domain.SendRequest;
import com.wqs.austin.service.api.domain.SendResponse;
import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.service.api.service.RecallService;
import com.wqs.austin.support.pipeline.ProcessContext;
import com.wqs.austin.support.pipeline.ProcessController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 撤回接口
 * author: wqs
 * date: 2022/10/15 18:17
 */
@Service
public class RecallServiceImpl implements RecallService {

    //流程控制器
    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse recall(SendRequest sendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .build();
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();
        ProcessContext process = processController.process(context);
        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }
}
