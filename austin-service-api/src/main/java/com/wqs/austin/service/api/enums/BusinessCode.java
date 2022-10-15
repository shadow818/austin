package com.wqs.austin.service.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * author: wqs
 * date: 2022/10/15 16:54
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {

    /**普通发送流程*/
    COMMON_SEND("send", "普通发送"),

    /**撤回流程*/
    RECALL("recall", "撤回消息");

    /**
     * code关联着责任链的模板
     */
    private String code;

    /** 类型说明*/
    private String description;

}
