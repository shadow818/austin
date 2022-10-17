package com.wqs.austin.handler.domain.sms;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 发送短信参数
 * author: wqs
 * date: 2022/10/17 16:03
 */
@Data
@Builder
public class SmsParam {

    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;
}

