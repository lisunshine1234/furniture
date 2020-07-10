package com.lzy.furniture.service;

import com.lzy.furniture.entity.Company;
import com.lzy.furniture.entity.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactService {
    /**
     * 用户端
     * 用户留言
     * @param message
     * @return
     */
    @Transactional
    public boolean messageSend(Message message);

    /**
     * 管理员端
     * 获取留言信息列表
     * @return
     */
    List<Message> getMessageList();

    /**
     * 管理员端
     * 更新留言信息的状态
     * @param message
     * @return
     */
    @Transactional
    boolean updateMessageState(Message message);

    /**
     * 管理员端
     * 删除留言
     * @param messId
     * @return
     */
    @Transactional
    boolean deleteMessage(Integer messId);
}
