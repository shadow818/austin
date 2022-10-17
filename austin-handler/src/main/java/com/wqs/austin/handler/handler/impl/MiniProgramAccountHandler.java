package com.wqs.austin.handler.handler.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.common.dto.model.MiniProgramContentModel;
import com.wqs.austin.common.enums.ChannelType;
import com.wqs.austin.handler.wechat.MiniProgramAccountService;
import com.wqs.austin.handler.domain.wechat.WeChatMiniProgramParam;
import com.wqs.austin.handler.handler.BaseHandler;
import com.wqs.austin.handler.handler.Handler;
import com.wqs.austin.support.domain.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 微信小程序发送订阅消息
 * author: wqs
 * date: 2022/10/17 15:30
 */
@Component
@Slf4j
public class MiniProgramAccountHandler extends BaseHandler implements Handler {
    @Autowired
    private MiniProgramAccountService miniProgramAccountService;

    public MiniProgramAccountHandler() {
        channelCode = ChannelType.MINI_PROGRAM.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        WeChatMiniProgramParam miniProgramParam = buildMiniProgramParam(taskInfo);
        try {
            miniProgramAccountService.send(miniProgramParam);
        } catch (Exception e) {
            log.error("MiniProgramAccountHandler#handler fail:{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(taskInfo));
            return false;
        }
        return true;
    }

    /**
     * 通过taskInfo构建小程序订阅消息
     *
     * @param taskInfo
     * @return
     */
    private WeChatMiniProgramParam buildMiniProgramParam(TaskInfo taskInfo) {
        // 小程序订阅消息可以关联到系统业务，通过接口查询。
        WeChatMiniProgramParam miniProgramParam = WeChatMiniProgramParam.builder()
                .openIds(taskInfo.getReceiver())
                .messageTemplateId(taskInfo.getMessageTemplateId())
                .sendAccount(taskInfo.getSendAccount())
                .build();

        MiniProgramContentModel contentModel = (MiniProgramContentModel) taskInfo.getContentModel();
        miniProgramParam.setData(contentModel.getMap());
        return miniProgramParam;
    }
    @Override
    public void recall(MessageTemplate messageTemplate) {

    }
}
