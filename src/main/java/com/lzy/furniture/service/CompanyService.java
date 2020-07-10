package com.lzy.furniture.service;

import com.lzy.furniture.entity.Company;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyService {
    /**
     * 用户端
     * 管理员端
     * 加载商家相关信息
     * @return Company实体类型
     */
    Company getCompanyInfo();

    /**
     * 管理员端
     * 保存修改的商家信息
     * @param company
     * @return
     */
    @Transactional
    boolean saveCompany(Company company);
}
