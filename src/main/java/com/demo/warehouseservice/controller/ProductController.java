package com.demo.warehouseservice.controller;


import com.demo.warehouseservice.domain.Product;
import com.demo.warehouseservice.dto.ProductDto;
import com.demo.warehouseservice.exception.ArticleNotFoundException;
import com.demo.warehouseservice.exception.FileFormatException;
import com.demo.warehouseservice.exception.ProductNotFoundException;
import com.demo.warehouseservice.exception.ProductOutOfStockException;
import com.demo.warehouseservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Gets all products.
     *
     * @return list of products.
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Gets product by id.
     *
     * @return products.
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    /**
     * Create a product.
     *
     * @return created product.
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto) throws ArticleNotFoundException {
        return ResponseEntity.ok(productService.addProduct(productDto));
    }

    /**
     * Import products response entity.
     *
     * @param inputData the input data
     * @return the response entity
     * @throws FileFormatException the validation exception
     */
    @PostMapping(path = "/import", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> importProducts(@RequestBody Map<String, List<ProductDto>> inputData)
            throws FileFormatException {
        List<ProductDto> data = inputData.get("products");
        if (Objects.nonNull(data)) {
            List<Product> products = productService.processProducts(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(products);
        }
        throw new FileFormatException("Error occurred while processing the file.");
    }

    /**
     * Sell product.
     *
     * @param id product id
     * @return product
     * @throws ProductNotFoundException product not found exception
     */
    @PutMapping(value = "/{id}/sell", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product sellProduct(@PathVariable Long id) throws ProductNotFoundException, ProductOutOfStockException {
        return productService.updateProductAndArticleInventory(id);
    }

}
