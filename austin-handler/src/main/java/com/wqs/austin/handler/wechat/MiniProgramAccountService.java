package com.wqs.austin.handler.wechat;

import com.wqs.austin.handler.domain.wechat.WeChatMiniProgramParam;

/**
 * author: wqs
 * date: 2022/10/17 15:34
 */
public interface MiniProgramAccountService {
    /**
     * 发送订阅消息
     *
     * @param miniProgramParam 订阅消息参数
     * @return
     * @throws Exception
     */
    void send(WeChatMiniProgramParam miniProgramParam) throws Exception;
}
