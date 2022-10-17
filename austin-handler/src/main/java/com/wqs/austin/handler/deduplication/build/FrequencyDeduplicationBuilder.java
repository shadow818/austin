package com.wqs.austin.handler.deduplication.build;

import cn.hutool.core.date.DateUtil;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.common.enums.AnchorState;
import com.wqs.austin.common.enums.DeduplicationType;
import com.wqs.austin.handler.deduplication.DeduplicationParam;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author: wqs
 * date: 2022/10/17 16:39
 */
@Service
public class FrequencyDeduplicationBuilder extends AbstractDeduplicationBuilder implements Builder {
    public FrequencyDeduplicationBuilder() {
        deduplicationType = DeduplicationType.FREQUENCY.getCode();
    }

    @Override
    public DeduplicationParam build(String deduplication, TaskInfo taskInfo) {
        DeduplicationParam deduplicationParam = getParamsFromConfig(deduplicationType, deduplication, taskInfo);
        if (deduplicationParam == null) {
            return null;
        }
        deduplicationParam.setDeduplicationTime((DateUtil.endOfDay(new Date()).getTime() - DateUtil.current()) / 1000);
        deduplicationParam.setAnchorState(AnchorState.RULE_DEDUPLICATION);
        return deduplicationParam;
    }
}

