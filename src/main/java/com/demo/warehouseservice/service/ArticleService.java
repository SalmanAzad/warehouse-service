package com.demo.warehouseservice.service;

import com.demo.warehouseservice.domain.Article;
import com.demo.warehouseservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
