package com.lzy.furniture.service;

import com.lzy.furniture.entity.Message;
import com.lzy.furniture.entity.News;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsService {
    List<News> getNewsAll();

    List<News> getNewsIsState();
    @Transactional
    boolean updateNewsState(News news);
    @Transactional
    boolean saveNews(News news);
    @Transactional
    boolean deleteNews(Integer newsId);

    News getNewsById(Integer newsId);

}
