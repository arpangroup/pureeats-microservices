package com.arpangroup.inventory.dto.request;

import com.arpangroup.inventory.common.StoreChargeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreCharge {
    @NotEmpty(message = "storeChargeType should not be empty or null")
    private String storeChargeType;

    private Float fixedStoreCharge;
    private Float fixedCommissionPercentage;

    private Float baseCommissionPercentage;
    private Float baseOrderAmount;
    private Float baseWeight;
    private Float extraCommissionPercentage;
    private Float extraOrderAmount;
    private Float extraWeight;
}
