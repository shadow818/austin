package com.wqs.austin.handler.deduplication.limit;

import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.handler.deduplication.DeduplicationParam;
import com.wqs.austin.handler.deduplication.service.AbstractDeduplicationService;

import java.util.Set;

/**
 * author: wqs
 * date: 2022/10/17 16:40
 */
public interface LimitService {


    /**
     * 去重限制
     * @param service 去重器对象
     * @param taskInfo
     * @param param 去重参数
     * @return 返回不符合条件的手机号码
     */
    Set<String> limitFilter(AbstractDeduplicationService service, TaskInfo taskInfo, DeduplicationParam param);

}
