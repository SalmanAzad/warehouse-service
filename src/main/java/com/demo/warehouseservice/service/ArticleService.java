package com.demo.warehouseservice.service;

import com.demo.warehouseservice.domain.Article;
import com.demo.warehouseservice.dto.ArticleDto;
import com.demo.warehouseservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article saveArticle(ArticleDto articleDto) {
        return articleRepository.save(Article.builder()
                .id(articleDto.getArticleId())
                .name(articleDto.getName())
                .stock(articleDto.getStock())
                .build());
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }
}
