package com.wqs.austin.handler.handler.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.wqs.austin.common.constant.AustinConstant;
import com.wqs.austin.common.constant.SendAccountConstant;
import com.wqs.austin.common.domain.TaskInfo;
import com.wqs.austin.common.dto.model.EnterpriseWeChatContentModel;
import com.wqs.austin.common.enums.ChannelType;
import com.wqs.austin.common.enums.SendMessageType;
import com.wqs.austin.handler.handler.BaseHandler;
import com.wqs.austin.handler.handler.Handler;
import com.wqs.austin.support.domain.MessageTemplate;
import com.wqs.austin.support.utils.AccountUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxMpErrorMsgEnum;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.WxCpMessageSendResult;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 企业微信推送处理
 * author: wqs
 * date: 2022/10/17 15:03
 */
@Component
@Slf4j
public class EnterpriseWeChatHandler extends BaseHandler implements Handler {
    /**
     * 切割userId 的分隔符
     */
    private static final String DELIMITER = "|";

    @Autowired
    private AccountUtils accountUtils;

    public EnterpriseWeChatHandler() {
        channelCode = ChannelType.ENTERPRISE_WE_CHAT.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        try {
            WxCpDefaultConfigImpl accountConfig = accountUtils.getAccount(taskInfo.getSendAccount(), SendAccountConstant.ENTERPRISE_WECHAT_ACCOUNT_KEY, SendAccountConstant.ENTERPRISE_WECHAT_PREFIX, WxCpDefaultConfigImpl.class);
            WxCpMessageServiceImpl messageService = new WxCpMessageServiceImpl(initService(accountConfig));
            WxCpMessageSendResult result = messageService.send(buildWxCpMessage(taskInfo, accountConfig.getAgentId()));
            if (Integer.valueOf(WxMpErrorMsgEnum.CODE_0.getCode()).equals(result.getErrCode())) {
                return true;
            }
            // 常见的错误 应当 关联至 AnchorState,由austin后台统一透出失败原因
            log.error("EnterpriseWeChatHandler#handler fail!result:{},params:{}", JSON.toJSONString(result), JSON.toJSONString(taskInfo));
        } catch (Exception e) {
            log.error("EnterpriseWeChatHandler#handler fail:{},params:{}",
                    Throwables.getStackTraceAsString(e), JSON.toJSONString(taskInfo));
        }
        return false;
    }


    /**
     * 初始化 WxCpServiceImpl 服务接口
     *
     * @param config
     * @return
     */
    private WxCpService initService(WxCpDefaultConfigImpl config) {
        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);
        return wxCpService;
    }

    /**
     * 构建企业微信下发消息的对象
     *
     * @param taskInfo
     * @param agentId  应用ID
     * @return
     */
    private WxCpMessage buildWxCpMessage(TaskInfo taskInfo, Integer agentId) {
        String userId;
        if (AustinConstant.SEND_ALL.equals(CollUtil.getFirst(taskInfo.getReceiver()))) {
            userId = CollUtil.getFirst(taskInfo.getReceiver());
        } else {
            userId = StringUtils.join(taskInfo.getReceiver(), DELIMITER);
        }
        EnterpriseWeChatContentModel contentModel = (EnterpriseWeChatContentModel) taskInfo.getContentModel();

        // 通用配置
        WxCpMessage wxCpMessage = null;

        if (SendMessageType.TEXT.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.TEXT().content(contentModel.getContent()).build();
        } else if (SendMessageType.IMAGE.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.IMAGE().mediaId(contentModel.getMediaId()).build();
        } else if (SendMessageType.VOICE.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.VOICE().mediaId(contentModel.getMediaId()).build();
        } else if (SendMessageType.VIDEO.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.VIDEO().mediaId(contentModel.getMediaId()).description(contentModel.getDescription()).title(contentModel.getTitle()).build();
        } else if (SendMessageType.FILE.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.FILE().mediaId(contentModel.getMediaId()).build();
        } else if (SendMessageType.TEXT_CARD.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.TEXTCARD().url(contentModel.getUrl()).title(contentModel.getTitle()).description(contentModel.getDescription()).btnTxt(contentModel.getBtnTxt()).build();
        } else if (SendMessageType.NEWS.getCode().equals(contentModel.getSendType())) {
            List<NewArticle> newArticles = JSON.parseArray(contentModel.getArticles(), NewArticle.class);
            wxCpMessage = WxCpMessage.NEWS().articles(newArticles).build();
        } else if (SendMessageType.MP_NEWS.getCode().equals(contentModel.getSendType())) {
            List<MpnewsArticle> mpNewsArticles = JSON.parseArray(contentModel.getMpNewsArticle(), MpnewsArticle.class);
            wxCpMessage = WxCpMessage.MPNEWS().articles(mpNewsArticles).build();
        } else if (SendMessageType.MARKDOWN.getCode().equals(contentModel.getSendType())) {
            wxCpMessage = WxCpMessage.MARKDOWN().content(contentModel.getContent()).build();
        } else if (SendMessageType.MINI_PROGRAM_NOTICE.getCode().equals(contentModel.getSendType())) {
            Map contentItems = JSON.parseObject(contentModel.getContentItems(), Map.class);
            wxCpMessage = WxCpMessage.newMiniProgramNoticeBuilder().appId(contentModel.getAppId()).page(contentModel.getPage()).emphasisFirstItem(contentModel.getEmphasisFirstItem()).contentItems(contentItems).title(contentModel.getTitle()).description(contentModel.getDescription()).build();
        } else if (SendMessageType.TEMPLATE_CARD.getCode().equals(contentModel.getSendType())) {
            // WxJava 未支持
        }
        wxCpMessage.setAgentId(agentId);
        wxCpMessage.setToUser(userId);
        return wxCpMessage;
    }

    @Override
    public void recall(MessageTemplate messageTemplate) {

    }
}