package com.wqs.austin.support.mq.eventbus;

import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.support.domain.MessageTemplate;

import java.util.List;

/**
 * 监听器
 * author: wqs
 * date: 2022/10/17 16:17
 */
public interface EventBusListener {


    /**
     * 消费消息
     * @param lists
     */
    void consume(List<TaskInfo> lists);

    /**
     * 撤回消息
     * @param messageTemplate
     */
    void recall(MessageTemplate messageTemplate);
}
