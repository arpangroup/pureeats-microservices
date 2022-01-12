package com.arpangroup.inventory.common;

import java.util.Arrays;

public enum DeliveryChargeType {
    FIXED("FIXED"),
    DYNAMIC("DYNAMIC");


    private final String value;
    DeliveryChargeType(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }


    public static DeliveryChargeType getDeliveryChargeType(String deliveryChargeType) {
        return Arrays.stream(DeliveryChargeType.values())
                .filter(storeType -> storeType.value.equals(deliveryChargeType))
                .findFirst()
                .orElse(null);
    }
}
