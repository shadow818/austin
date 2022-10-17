package com.wqs.austin.handler.wechat;

import com.wqs.austin.handler.domain.wechat.WeChatOfficialParam;

import java.util.List;

/**
 * author: wqs
 * date: 2022/10/17 15:37
 */
public interface OfficialAccountService {
    /**
     * 发送模板消息
     *
     * @param weChatOfficialParam 模板消息参数
     * @return
     * @throws Exception
     */
    List<String> send(WeChatOfficialParam weChatOfficialParam) throws Exception;
}
