package com.lzy.furniture.repository;

import com.lzy.furniture.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Message,Integer> {
    @Query(value = "select count(*) from message where state = 0",nativeQuery = true)
    Long getMessageNotReadCount();

}
