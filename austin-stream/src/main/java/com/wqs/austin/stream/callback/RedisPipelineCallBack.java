package com.wqs.austin.stream.callback;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.List;

/**
 * redis pipeline接口定义
 * author: wqs
 * date: 2022/10/17 19:20
 */
public interface RedisPipelineCallBack {

    /**
     * 具体执行逻辑
     *
     * @param redisAsyncCommands
     * @return
     */
    List<RedisFuture<?>> invoke(RedisAsyncCommands redisAsyncCommands);

}
