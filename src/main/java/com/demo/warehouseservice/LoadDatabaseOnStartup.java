package com.demo.warehouseservice;

import com.demo.warehouseservice.dto.ArticleDto;
import com.demo.warehouseservice.dto.ProductDto;
import com.demo.warehouseservice.exception.ArticleNotFoundException;
import com.demo.warehouseservice.service.ArticleService;
import com.demo.warehouseservice.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Log4j2
@Configuration
public class LoadDatabaseOnStartup {

    private final ArticleService articleService;

    private final ProductService productService;

    @Autowired
    public LoadDatabaseOnStartup(ArticleService articleService, ProductService productService) {
        this.articleService = articleService;
        this.productService = productService;
    }

    @Bean
    public void loadFiles() throws IOException {

        String[] files = {"inventory.json", "products.json"};
        ClassLoader classLoader = WarehouseServiceApplication.class.getClassLoader();
        for (String file : files) {
            processFile(new File(Objects.requireNonNull(classLoader.getResource(file)).getFile()));
        }
    }

    private void processFile(File file) throws FileNotFoundException {
        InputStream stream = new FileInputStream(file);
        try (InputStreamReader inputStreamReader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            // create `JsonReader` instance
            JsonReader reader = new JsonReader(inputStreamReader);
            Gson gson = new Gson();

            // read data
            reader.beginObject();
            while (reader.hasNext()) {
                 reader.nextName();
                reader.beginArray();
                if(file.getName().contains("inventory")) {
                    while (reader.hasNext()) {
                        ArticleDto articleDto = gson.fromJson(reader, ArticleDto.class);
                        System.out.println(articleDto.toString());
                        articleService.saveArticle(articleDto);
                    }
                } else if(file.getName().contains("products")){
                    while (reader.hasNext()) {
                        ProductDto productDto = gson.fromJson(reader, ProductDto.class);
                        System.out.println(productDto.toString());
                        try {
                            productService.addProduct(productDto);
                        } catch (ArticleNotFoundException articleNotFoundException) {
                            articleNotFoundException.printStackTrace();
                        }
                    }
                }
                else {
                    log.error("Unsupported file");
                }
                reader.endArray();
            }
            reader.endObject();
            reader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
