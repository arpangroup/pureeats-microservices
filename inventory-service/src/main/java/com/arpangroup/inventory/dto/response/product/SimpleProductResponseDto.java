package com.arpangroup.inventory.dto.response.product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleProductResponseDto {
    protected int productId;
    protected String productName;

    public SimpleProductResponseDto(int productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }
}
