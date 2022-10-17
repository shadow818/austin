package com.wqs.austin.handler.domain.wechat;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * 小程序参数
 * author: wqs
 * date: 2022/10/17 15:35
 */
@Data
@Builder
public class WeChatMiniProgramParam {
    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 发送账号
     */
    private Integer sendAccount;

    /**
     * 接收者（用户）的 openid
     */
    private Set<String> openIds;

    /**
     * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     */
    private Map<String, String> data;

}
