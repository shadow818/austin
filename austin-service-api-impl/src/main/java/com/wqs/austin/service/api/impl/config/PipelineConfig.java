package com.wqs.austin.service.api.impl.config;

import com.wqs.austin.service.api.enums.BusinessCode;
import com.wqs.austin.service.api.impl.action.AfterParamCheckAction;
import com.wqs.austin.service.api.impl.action.AssembleAction;
import com.wqs.austin.service.api.impl.action.PreParamCheckAction;
import com.wqs.austin.service.api.impl.action.SendMqAction;
import com.wqs.austin.support.pipeline.ProcessController;
import com.wqs.austin.support.pipeline.ProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * api层的pipeline配置类
 * author: wqs
 * date: 2022/10/15 18:19
 */
@Configuration
public class PipelineConfig {

    //前置检查
    @Autowired
    private PreParamCheckAction preParamCheckAction;

    //组合参数
    @Autowired
    private AssembleAction assembleAction;

    //后置检查
    @Autowired
    private AfterParamCheckAction afterParamCheckAction;

    //发送消息到MQ
    @Autowired
    private SendMqAction sendMqAction;


    /**
     * 发送流程
     *  1. 前置参数检查
     *  2. 组装参数
     *  3. 后置参数校验
     *  4. 发送消息至MQ
     * @return
     */
    @Bean
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(preParamCheckAction, assembleAction, afterParamCheckAction, sendMqAction));
        return processTemplate;
    }

    /**
     * 撤回消息
     *  1. 组装参数
     *  2. 发送MQ
     * @return
     */
    @Bean
    public ProcessTemplate recallMessageTemplate(){
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(assembleAction, sendMqAction));
        return processTemplate;
    }

    /**
     * pipeline流程控制器
     * @return
     */
    @Bean
    public ProcessController processController(){
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        templateConfig.put(BusinessCode.RECALL.getCode(), recallMessageTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }


}
