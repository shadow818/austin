package com.wqs.austin.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 发送/撤回接口的参数
 * author: wqs
 * date: 2022/10/15 16:39
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendRequest {

    /**
     * 执行业务的类型
     * send: 发送消息
     * recall: 撤回消息
     */
    private String code;

    /**
     * 消息模板id, 必填
     */
    private Long messageTemplateId;

    /**
     * 消息相关的参数
     * 当前业务类型为"send", 必传
     */
    private MessageParam messageParam;


}
