package com.wqs.austin.support.pipeline;

import java.util.List;

/**
 * 业务执行模板
 * author: wqs
 * date: 2022/10/15 17:20
 */
public class ProcessTemplate {
    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }
    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
