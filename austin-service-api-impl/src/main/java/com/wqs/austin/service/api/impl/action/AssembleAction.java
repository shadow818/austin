package com.wqs.austin.service.api.impl.action;

import com.wqs.austin.service.api.impl.domain.SendTaskModel;
import com.wqs.austin.support.pipeline.BusinessProcess;
import com.wqs.austin.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 拼接参数
 * author: wqs
 * date: 2022/10/15 19:49
 */
@Slf4j
@Service
public class AssembleAction implements BusinessProcess<SendTaskModel> {



    @Override
    public void process(ProcessContext<SendTaskModel> context) {

    }
}
