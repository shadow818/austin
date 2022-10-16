package com.wqs.austin.service.api.impl.domain;

import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.service.api.domain.MessageParam;
import com.wqs.austin.support.domain.MessageTemplate;
import com.wqs.austin.support.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 发送消息任务的模型
 * author: wqs
 * date: 2022/10/15 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;

    /**
     * 撤回任务的信息
     */
    private MessageTemplate messageTemplate;

}
