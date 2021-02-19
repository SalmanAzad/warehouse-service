package com.demo.warehouseservice.controller;

import com.demo.warehouseservice.domain.Article;
import com.demo.warehouseservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Gets all articles.
     *
     * @return list of articles.
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * Created an article.
     *
     * @return created article.
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> saveArticle(@RequestBody Article article) {
            articleService.saveArticle(article);
            return ResponseEntity.ok(article);
    }
}
