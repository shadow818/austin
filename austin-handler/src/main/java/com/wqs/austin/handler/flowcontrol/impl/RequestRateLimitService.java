package com.wqs.austin.handler.flowcontrol.impl;

import com.google.common.util.concurrent.RateLimiter;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.handler.enums.RateLimitStrategy;
import com.wqs.austin.handler.flowcontrol.FlowControlParam;
import com.wqs.austin.handler.flowcontrol.FlowControlService;
import com.wqs.austin.handler.flowcontrol.annotations.LocalRateLimit;

/**
 * author: wqs
 * date: 2022/10/17 16:45
 */
@LocalRateLimit(rateLimitStrategy = RateLimitStrategy.REQUEST_RATE_LIMIT)
public class RequestRateLimitService implements FlowControlService {

    /**
     * 根据渠道进行流量控制
     *
     * @param taskInfo
     * @param flowControlParam
     */
    @Override
    public Double flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam) {
        RateLimiter rateLimiter = flowControlParam.getRateLimiter();
        return rateLimiter.acquire(1);
    }
}