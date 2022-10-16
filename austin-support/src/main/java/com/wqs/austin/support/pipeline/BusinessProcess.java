package com.wqs.austin.support.pipeline;

/**
 * 业务执行器
 * author: wqs
 * date: 2022/10/15 17:21
 */
public interface BusinessProcess<T extends ProcessModel> {
    void process(ProcessContext<T> context);
}
