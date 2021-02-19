package com.demo.warehouseservice.service;

import com.demo.warehouseservice.domain.Article;
import com.demo.warehouseservice.domain.Product;
import com.demo.warehouseservice.domain.ProductArticle;
import com.demo.warehouseservice.dto.ProductArticleDto;
import com.demo.warehouseservice.dto.ProductDto;
import com.demo.warehouseservice.exception.ArticleNotFoundException;
import com.demo.warehouseservice.repository.ArticleRepository;
import com.demo.warehouseservice.repository.ProductArticleRepository;
import com.demo.warehouseservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;

    private final ArticleRepository articleRepository;

    private final ProductArticleRepository productArticleRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ArticleRepository articleRepository, ProductArticleRepository productArticleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.productArticleRepository = productArticleRepository;
    }

    /**
     *
     * @return a product
     */
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    /**
     *
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Add a product
     *
     * @param productDto the product dto
     * @return the product
     * @throws ArticleNotFoundException the article validation exception
     */
    @Transactional
    public Product addProduct(final ProductDto productDto) throws ArticleNotFoundException {
        List<ProductArticle> productArticles = new ArrayList<>();
        Product product = Product.builder()
                .name(productDto.getName())
                .price(Objects.nonNull(productDto.getPrice()) ? productDto.getPrice() : BigDecimal.ZERO)
                .build();
        for (ProductArticleDto productArticleDto : productDto.getProductArticles()) {
            Article article = articleRepository.findById(productArticleDto.getArticleId())
                    .orElseThrow(() ->
                            new ArticleNotFoundException(String.format("Article not found :: %s",
                                    productArticleDto.getArticleId())));
            productArticles.add(ProductArticle.builder()
                    .product(product)
                    .article(article)
                    .quantity(productArticleDto.getQuantity())
                    .build());
        }
        productArticleRepository.saveAll(productArticles);
        productRepository.save(product);
        return product;
    }

    /**
     * Process products list.
     *
     * @param productDtos the product
     * @return the list
     * @throws ArticleNotFoundException the product validation exception
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Product> processProducts(List<ProductDto> productDtos) {
        List<Product> products = new ArrayList<>();
        try {
            for (ProductDto productDto : productDtos) {
                Product product = this.addProduct(productDto);
                products.add(product);
            }
        } catch (ArticleNotFoundException articleNotFoundException){
           log.error(articleNotFoundException.getMessage());
        }
        return products;
    }
}
