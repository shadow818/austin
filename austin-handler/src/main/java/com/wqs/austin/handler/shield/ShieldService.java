package com.wqs.austin.handler.shield;

import com.wqs.austin.common.domain.TaskInfo;

/**
 * 屏蔽服务
 * author: wqs
 * date: 2022/10/17 16:33
 */
public interface ShieldService {


    /**
     * 屏蔽消息
     * @param taskInfo
     */
    void shield(TaskInfo taskInfo);
}
