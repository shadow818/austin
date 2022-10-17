package com.wqs.austin.handler.flowcontrol;

import com.wqs.austin.common.domain.TaskInfo;

/**
 * 流浪控制服务
 * author: wqs
 * date: 2022/10/17 13:56
 */
public interface FlowControlService {

    /**
     * 根据渠道进行流量控制
     *
     * @param taskInfo
     * @param flowControlParam
     */
    Double flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam);
}
