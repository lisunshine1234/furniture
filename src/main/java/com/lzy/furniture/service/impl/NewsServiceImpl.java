package com.lzy.furniture.service.impl;

import com.lzy.furniture.entity.News;
import com.lzy.furniture.entity.News;
import com.lzy.furniture.repository.NewsRepository;
import com.lzy.furniture.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getNewsAll() {
        return utilNewsHelper(newsRepository.findAll());
    }

    @Override
    public List<News> getNewsIsState() {
        return utilNewsHelper(newsRepository.findAllByState(1));
    }

    @Override
    public boolean updateNewsState(News news) {
        newsRepository.save(news);
        return true;
    }

    @Override
    public boolean saveNews(News news) {
        newsRepository.save(news);
        return true;
    }

    @Override
    public boolean deleteNews(Integer newsId) {
        newsRepository.deleteById(newsId);
        return true;
    }

    @Override
    public News getNewsById(Integer newsId) {
        return utilNewsHelper(newsRepository.findById(newsId).orElse(new News()));
    }


    private List<News> utilNewsHelper(List<News> newsList) {
        String filename1 = "";
        String filenameUrl1 = "";
        String fileBaseName1 = "";
        String fileType1 = "";
        for (News news : newsList) {
            if (news.getAddress() != null && news.getAddress().length() > 0) {
                filename1 = news.getAddress().substring(news.getAddress().lastIndexOf("/") + 1);
                filenameUrl1 = news.getAddress().substring(0, news.getAddress().length() - filename1.length() - 1);
                fileType1 = filename1.substring(filename1.lastIndexOf(".") + 1);
                fileBaseName1 = filename1.substring(0, filename1.length() - fileType1.length() - 1);
                news.setAddressMini(filenameUrl1 +"/"+ fileBaseName1 + "_mini." + fileType1);
                news.setAddressMiddle(filenameUrl1 +"/"+ fileBaseName1 + "_middle." + fileType1);
                news.setAddressLarge(filenameUrl1 +"/"+ fileBaseName1 + "_large." + fileType1);
            }
        }
        return newsList;
    }


    private News utilNewsHelper(News news) {
        String filename1 = "";
        String filenameUrl1 = "";
        String fileBaseName1 = "";
        String fileType1 = "";
        if (news.getAddress() != null && news.getAddress().length() > 0) {
            filename1 = news.getAddress().substring(news.getAddress().lastIndexOf("/") + 1);
            filenameUrl1 = news.getAddress().substring(0, news.getAddress().length() - filename1.length() - 1);
            fileType1 = filename1.substring(filename1.lastIndexOf(".") + 1);
            fileBaseName1 = filename1.substring(0, filename1.length() - fileType1.length() - 1);
            news.setAddressMini(filenameUrl1 +"/"+ fileBaseName1 + "_mini." + fileType1);
            news.setAddressMiddle(filenameUrl1 +"/"+ fileBaseName1 + "_middle." + fileType1);
            news.setAddressLarge(filenameUrl1 +"/"+ fileBaseName1 + "_large." + fileType1);
        }
        return news;
    }
}
