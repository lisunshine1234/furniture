package com.lzy.furniture.repository;

import com.lzy.furniture.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {
    List<News> findAllByState(Integer state);
}
