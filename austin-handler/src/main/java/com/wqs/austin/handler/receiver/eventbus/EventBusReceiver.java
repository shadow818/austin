package com.wqs.austin.handler.receiver.eventbus;

import com.google.common.eventbus.Subscribe;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.handler.receiver.service.ConsumeService;
import com.wqs.austin.support.constans.MessageQueuePipeline;
import com.wqs.austin.support.domain.MessageTemplate;
import com.wqs.austin.support.mq.eventbus.EventBusListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author: wqs
 * date: 2022/10/17 16:16
 */
@Component
@ConditionalOnProperty(name = "austin.mq.pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusReceiver implements EventBusListener {

    @Autowired
    private ConsumeService consumeService;

    @Override
    @Subscribe
    public void consume(List<TaskInfo> lists) {
        consumeService.consume2Send(lists);

    }

    @Override
    @Subscribe
    public void recall(MessageTemplate messageTemplate) {
        consumeService.consume2recall(messageTemplate);
    }
}
