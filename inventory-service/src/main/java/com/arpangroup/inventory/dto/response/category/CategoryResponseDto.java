package com.arpangroup.inventory.dto.response.category;

import com.arpangroup.inventory.dto.response.product.SimpleProductResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"categoryId", "categoryName", "description", "image", "isActive"})
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDto {
    @Schema(description = "Unique identifier of the category.", example = "1", required = true)
    private int categoryId;

    @Schema(description = "Name of the category.", example = "Chicken", required = true)
    @NotBlank
    private String categoryName;

    @Schema(description = "description of the category. ", example = "A chicken is a bird. One of the features that differentiate it from most other birds is that it has a comb and two wattles.", required = false)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;

    @Schema(description = "image URL of the category. ", example = "https://picsum.photos/1280/720?random=1", required = false)
    private String image;

    @Schema(description = "is the category currently available/active", example = "false", required = false)
    private boolean isActive;

    private List<SimpleProductResponseDto> products;

    public void addProduct(SimpleProductResponseDto product){
        if (this.products == null){
            this.products = new ArrayList<>();
        }
        products.add(product);
    }

}
