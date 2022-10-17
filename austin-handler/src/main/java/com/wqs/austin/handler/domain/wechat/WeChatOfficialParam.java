package com.wqs.austin.handler.domain.wechat;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * 服务号参数
 * author: wqs
 * date: 2022/10/17 15:38
 */
@Data
@Builder
public class WeChatOfficialParam {
    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 关注服务号得用户
     */
    private Set<String> openIds;

    /**
     * 模板消息的信息载体
     */
    private Map<String, String> data;

    /**
     * 发送账号
     */
    private Integer sendAccount;
}
