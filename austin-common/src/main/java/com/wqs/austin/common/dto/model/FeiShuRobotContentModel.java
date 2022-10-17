package com.wqs.austin.common.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 飞书机器人
 * https://open.feishu.cn/document/ukTMukTMukTM/ucTM5YjL3ETO24yNxkjN#756b882f
 * author: wqs
 * date: 2022/10/17 14:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeiShuRobotContentModel extends ContentModel {

    /**
     * 发送类型
     */
    private String sendType;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送标题
     */
    private String title;

    /**
     * 媒体Id
     */
    private String mediaId;


    /**
     * 富文本内容：[[{"tag":"text","text":"项目有更新: "},{"tag":"a","text":"请查看","href":"http://www.example.com/"},{"tag":"at","user_id":"ou_18eac8********17ad4f02e8bbbb"}]]
     */
    private String postContent;

}
