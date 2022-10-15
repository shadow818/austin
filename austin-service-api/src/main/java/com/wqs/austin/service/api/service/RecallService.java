package com.wqs.austin.service.api.service;

import com.wqs.austin.service.api.domain.SendRequest;
import com.wqs.austin.service.api.domain.SendResponse;

/**
 * 撤回接口
 * author: wqs
 * date: 2022/10/15 17:05
 */
public interface RecallService {

    SendResponse recall(SendRequest sendRequest);

}
