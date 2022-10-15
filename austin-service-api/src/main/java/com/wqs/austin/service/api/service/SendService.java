package com.wqs.austin.service.api.service;

import com.wqs.austin.service.api.domain.BatchSendRequest;
import com.wqs.austin.service.api.domain.SendRequest;
import com.wqs.austin.service.api.domain.SendResponse;

/**
 * 发送接口
 * author: wqs
 * date: 2022/10/15 17:03
 */
public interface SendService {

    /**
     * 单模板单文案发送接口
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 单模板多文案发送接口
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);
}
