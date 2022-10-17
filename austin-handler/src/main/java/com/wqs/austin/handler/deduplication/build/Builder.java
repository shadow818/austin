package com.wqs.austin.handler.deduplication.build;

import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.handler.deduplication.DeduplicationParam;

/**
 * author: wqs
 * date: 2022/10/17 16:29
 */
public interface Builder {

    String DEDUPLICATION_CONFIG_PRE = "deduplication_";

    /**
     * 根据配置构建去重参数
     *
     * @param deduplication
     * @param taskInfo
     * @return
     */
    DeduplicationParam build(String deduplication, TaskInfo taskInfo);
}
