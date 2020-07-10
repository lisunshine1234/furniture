package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.VisitTotle;
import com.lzy.furniture.repository.ContactRepository;
import com.lzy.furniture.repository.ProductRepository;
import com.lzy.furniture.repository.UserRepository;
import com.lzy.furniture.repository.VisitTotleRepository;
import com.lzy.furniture.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VisitTotleRepository visitTotleRepository;

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public Long getMessageCount() {
        return contactRepository.count();
    }

    @Override
    public Long getProductCount() {
        return productRepository.count();
    }

    @Override
    public Long getVisitCount() {
        return productRepository.getVisitCount();
    }

    @Override
    public Long getMessageNotReadCount() {
        return contactRepository.getMessageNotReadCount();
    }

    @Override
    public Map<String, List<String>> getWeekVisit() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -6);

        List<Map<String, Object>> dateCount = visitTotleRepository.getWeekVisit();

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        int j = 0;
        for (int i = 0; i < 7; i++) {
            String time = format.format(calendar.getTime());
            list1.add(time);

            if (j < dateCount.size()) {
                if (dateCount.get(j).get("date").equals(time)) {
                    list2.add(dateCount.get(j).get("count").toString());
                    j++;
                } else {
                    list2.add("0");
                }
            } else {
                list2.add("0");
            }

            calendar.add(Calendar.DAY_OF_MONTH, +1);
        }

        map.put("date", list1);
        map.put("count", list2);

        return map;
    }


}
