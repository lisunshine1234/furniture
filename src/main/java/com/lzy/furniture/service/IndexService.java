package com.lzy.furniture.service;

import java.util.List;
import java.util.Map;

public interface IndexService {
    /**
     * 管理端
     * 获取用户的个数
     *
     * @return 用户数
     */
    Long getUserCount();

    /**
     * 管理端
     * 获取邮件数量
     *
     * @return 邮件数
     */
    Long getMessageCount();

    /**
     * 管理端
     * 获取产品数量
     *
     * @return 产品数
     */
    Long getProductCount();

    /**
     * 管理端
     * 获取浏览数量
     *
     * @return 浏览数
     */
    Long getVisitCount();

    /**
     * 管理端
     * 获取未读邮件数量
     *
     * @return
     */
    Long getMessageNotReadCount();

    /**
     * 管理端
     * 获取一周之内的访问量
     *
     * @return
     */
    Map<String, List<String>> getWeekVisit();
}
