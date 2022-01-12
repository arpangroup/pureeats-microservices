package com.arpangroup.onboarding.common;

import java.util.Arrays;

public enum DeliveryType {
    DELIVERY("DELIVERY"), // WaitingForRestaurantToAcceptOrder
    SELF_PICKUP("SELF_PICKUP"),//Food is being prepared/Chef at work/Restaurant is preparing your order
    BOTH("BOTH"); //On the way to pikup you order


    public final String value;
    DeliveryType(String id){
        this.value = id;
    }


    public String value() {
        return value;
    }

    public static DeliveryType getDeliveryTypeById(String deliveryType) {
        return Arrays.stream(DeliveryType.values())
                .filter(d -> d.value == deliveryType)
                .findFirst()
                .orElse(null);
    }
}
