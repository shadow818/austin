package com.wqs.austin.common.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建个推账号时的元信息
 * https://docs.getui.com/getui/start/devcenter/
 * author: wqs
 * date: 2022/10/17 15:43
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeTuiAccount {

    private String appId;

    private String appKey;

    private String masterSecret;
}
