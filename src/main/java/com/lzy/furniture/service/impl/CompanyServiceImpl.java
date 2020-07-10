package com.lzy.furniture.service.impl;

import com.lzy.furniture.config.redis.RedisService;
import com.lzy.furniture.entity.Company;
import com.lzy.furniture.entity.NavigationFather;
import com.lzy.furniture.repository.CompanyRepository;
import com.lzy.furniture.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RedisService redisService;
    @Override
    public Company getCompanyInfo() {
        Company company;
        if (redisService.hasKey("furniture:company")) {
            company = (Company) redisService.get("furniture:company");
        } else {
            company = companyRepository.findById(1).orElse(new Company());
            redisService.set("furniture:company", company, 1800);
        }
        return company;
    }


    @Override
    public boolean saveCompany(Company company) {
        companyRepository.save(company);
        redisService.del("furniture:company");
        return true;
    }
}
