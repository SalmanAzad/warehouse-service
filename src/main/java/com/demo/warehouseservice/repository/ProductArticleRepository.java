package com.demo.warehouseservice.repository;

import com.demo.warehouseservice.domain.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {
}
