package com.wqs.austin.handler.handler;

import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.support.domain.MessageTemplate;

/**
 * 消息处理器
 * author: wqs
 * date: 2022/10/17 13:39
 */
public interface Handler {

    /**
     * 消息处理
     * @param taskInfo 业务信息
     */
    void doHandler(TaskInfo taskInfo);

    /**
     * 消息撤回
     * @param messageTemplate 消息模板
     */
    void recall(MessageTemplate messageTemplate);


}
