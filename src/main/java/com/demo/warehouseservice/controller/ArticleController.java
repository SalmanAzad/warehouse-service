package com.demo.warehouseservice.controller;

import com.demo.warehouseservice.domain.Article;
import com.demo.warehouseservice.dto.ArticleDto;
import com.demo.warehouseservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
     * Get an article.
     *
     * @return A article.
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {

        return articleService.getArticle(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Created an article.
     *
     * @return created article.
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> saveArticle(@RequestBody ArticleDto articleDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(articleService.saveArticle(articleDto));
    }
}
