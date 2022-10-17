package com.wqs.austin.common.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知栏消息推送
 * author: wqs
 * date: 2022/10/17 14:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImContentModel extends ContentModel{
    private String title;
    private String content;
    private String url;
}
