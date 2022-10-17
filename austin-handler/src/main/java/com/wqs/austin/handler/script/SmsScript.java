package com.wqs.austin.handler.script;

import com.wqs.austin.handler.domain.sms.SmsParam;
import com.wqs.austin.support.domain.SmsRecord;

import java.util.List;

/**
 * 短信脚本 接口
 * author: wqs
 * date: 2022/10/17 16:03
 */
public interface SmsScript {

    /**
     * 发送短信
     * @param smsParam
     * @return 渠道商接口返回值

     */
    List<SmsRecord> send(SmsParam smsParam);

}