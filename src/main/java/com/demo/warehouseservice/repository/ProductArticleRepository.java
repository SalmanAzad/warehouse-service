package com.demo.warehouseservice.repository;

import com.demo.warehouseservice.domain.Product;
import com.demo.warehouseservice.domain.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {
    /**
     * Find all ProductArticles by product.
     *
     * @param product product
     * @return the list of product Articles
     */
    List<ProductArticle> findAllByProduct(Product product);
}
