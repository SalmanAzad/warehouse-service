package com.demo.warehouseservice.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable {

    private static final long serialVersionUID = -3661539114223766848L;

    private String name;

    private BigDecimal price;

    @JsonProperty("contain_articles")
    @SerializedName("contain_articles")
    private List<ProductArticleDto> productArticles;
}
