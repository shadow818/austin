package com.wqs.austin.common.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 钉钉自定义机器人 账号信息
 * author: wqs
 * date: 2022/10/17 14:23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DingDingRobotAccount {

    /**
     * 密钥
     */
    private String secret;

    /**
     * 自定义群机器人中的 webhook
     */
    private String webhook;

}
