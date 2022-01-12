package com.arpangroup.inventory.dto.response.product;

import com.arpangroup.inventory.entity.TagEntity;
import com.arpangroup.inventory.mapper.util.NullSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto{
    private int categoryId;
    private Float purchasePrice;
    private Float sellingPrice;
    private Float defaultPrice;
    private Float displayPrice;
    private String image;
    @JsonSerialize(using = NullSerializer.class)
    private String description;
    private String itemState;
    private String itemType;
    private boolean isVeg;
    private boolean isRecommended;
    private boolean isPopular;
    private List<TagEntity> tagEntities;
}
