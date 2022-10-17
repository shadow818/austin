package com.wqs.austin.handler.deduplication.service;

import com.wqs.austin.handler.deduplication.DeduplicationParam;

/**
 * author: wqs
 * date: 2022/10/17 16:31
 */
public interface DeduplicationService {

    /**
     * 去重
     * @param param
     */
    void deduplication(DeduplicationParam param);
}

