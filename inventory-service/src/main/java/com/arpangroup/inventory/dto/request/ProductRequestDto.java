package com.arpangroup.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductRequestDto implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;

    @Schema(description = "restaurant id ", example = "186352", required = true)
    @NotNull(message = "should not be null")
    @JsonProperty("store_id")
    private Integer storeId;

    @Schema(description = "valid category id ", example = "783", required = true)
    @JsonProperty("product_category_id")
    @NotNull(message = "should not be null")
    private Integer productCategoryId;


    @Schema(description = "name of the product ", example = "Chicken Tandoori", required = true)
    @JsonProperty("product_name")
    @NotEmpty(message = "should not be empty or null")
    @Length(min = 3, max = 240, message = "should be minimum 3 characters long")
    private String productName;

    /*##################################################################*/
    @JsonProperty("purchase_price")
    @NotNull(message = "purchase_price should not be null")
    private Float purchasePrice;

    @JsonProperty("selling_price")
    @NotNull(message = "selling_price should not be null")
    private Float sellingPrice;

    @JsonProperty("default_price")
    private Float defaultPrice;//oldPrice

    @JsonProperty("display_price")
    private Float displayPrice;//price
    /*##################################################################*/

    private String image;
    private String description;


    @JsonProperty("is_veg")
    private Boolean isVeg;
    @JsonProperty("is_recommended")
    private Boolean isRecommended;
    @JsonProperty("is_popular")
    private Boolean isPopular;

    private List<String> tags;
}
