package com.arpangroup.inventory.common;

import java.util.Arrays;

public enum StoreChargeType {
    FIXED("FIXED"),
    DYNAMIC("DYNAMIC"),
    PERCENTAGE("PERCENTAGE");


    private final String value;
    StoreChargeType(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }


    public static StoreChargeType getStoreChargeType(String deliveryChargeType) {
        return Arrays.stream(StoreChargeType.values())
                .filter(storeType -> storeType.value.equals(deliveryChargeType))
                .findFirst()
                .orElse(null);
    }
}
