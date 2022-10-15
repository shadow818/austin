package com.wqs.austin.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * author: wqs
 * date: 2022/10/15 16:52
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SendResponse {

    /**
     * 响应状态
     */
    private String code;


    /**
     * 响应编码
     */
    private String msg;


}
