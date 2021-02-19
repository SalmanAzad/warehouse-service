package com.demo.warehouseservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto implements Serializable {

    @JsonProperty("art_id")
    @SerializedName("art_id")
    private Long articleId;

    private String name;

    private Long stock;
}
