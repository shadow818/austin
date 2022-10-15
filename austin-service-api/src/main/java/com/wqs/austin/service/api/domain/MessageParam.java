package com.wqs.austin.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 消息参数
 * author: wqs
 * date: 2022/10/15 16:42
 */
@Data
@Accessors(chain = true) //set方法返回的是对象的实例，可以直接再使用set方法或者直接调用函数
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageParam {

    /**
     * 消息的接收者
     */
    private String receiver;

    /**
     * 消息内容中的可变部分
     */
    private Map<String, String> variables;

    /**
     * 扩展参数，可选
     */
    private Map<String, String> extra;

}
