package com.arpangroup.onboarding.common;

import java.util.Arrays;

public enum StoreType {
    RESTAURANT("RESTAURANT"),
    GROCERY("GROCERY"),
    BAVERY("BAVERY"),
    FOOD_COURT("FOOD_COURT"),
    DINING("DINING"),
    QUICK_BITES("QUICK_BITES"),
    BEVERAGE_SHOP("BEVERAGE_SHOP"),
    DESERT("DESERT"),
    SWEET_SHOP("SWEET_SHOP"),
    CAFE("CAFE");


    private final String value;
    StoreType(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }


    public static StoreType getStoreType(String storeTypeValue) {
        return Arrays.stream(StoreType.values())
                .filter(storeType -> storeType.value.equals(storeTypeValue))
                .findFirst()
                .orElse(null);
    }
}
