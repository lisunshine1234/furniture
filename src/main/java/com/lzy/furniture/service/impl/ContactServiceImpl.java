package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.Company;
import com.lzy.furniture.entity.Message;
import com.lzy.furniture.entity.User;
import com.lzy.furniture.repository.CompanyRepository;
import com.lzy.furniture.repository.ContactRepository;
import com.lzy.furniture.repository.UserRepository;
import com.lzy.furniture.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public boolean messageSend(Message message) {
        contactRepository.save(message);
        return true;
    }

    @Override
    public List<Message> getMessageList() {
        List<Message> messagesList = contactRepository.findAll();
        List<Message> list = new ArrayList<Message>();
        for(Message message : messagesList){
            message.setUserName(userRepository.findById(message.getUserId()).orElse(new User()).getLoginName());
            list.add(message);
        }
        return list;
    }

    @Override
    public boolean updateMessageState(Message message) {
        contactRepository.save(message);
        return true;
    }

    @Override
    public boolean deleteMessage(Integer messId) {
        contactRepository.deleteById(messId);
        return true;
    }


}
