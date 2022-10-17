package com.wqs.austin.support.dao;

import com.wqs.austin.support.domain.ChannelAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 渠道账号信息 Dao
 * author: wqs
 * date: 2022/10/17 16:53
 */
public interface ChannelAccountDao extends CrudRepository<ChannelAccount, Long> {


    /**
     * 查询 列表（分页)
     *
     * @param deleted     0：未删除 1：删除
     * @param channelType 渠道值
     * @return
     */
    List<ChannelAccount> findAllByIsDeletedEqualsAndSendChannelEquals(Integer deleted, Integer channelType);


    /**
     * 统计未删除的条数
     *
     * @param deleted
     * @return
     */
    Long countByIsDeletedEquals(Integer deleted);
}
