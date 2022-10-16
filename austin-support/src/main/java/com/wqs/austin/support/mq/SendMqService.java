package com.wqs.austin.support.mq;

/**
 * 发送数据至消息队列
 * author: wqs
 * date: 2022/10/16 22:30
 */
public interface SendMqService {

    /**
     * 发送消息
     * @param topic topic名字
     * @param jsonValue json格式的字符串
     * @param tagId tagId
     */
    void send(String topic, String jsonValue, String tagId);

    void send(String topic, String josnValue);

}
