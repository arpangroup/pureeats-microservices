package com.arpangroup.inventory.common;

import java.util.Arrays;

public enum DeliveryType {
    DELIVERY(1), // WaitingForRestaurantToAcceptOrder
    SELF_PICKUP(2),//Food is being prepared/Chef at work/Restaurant is preparing your order
    BOTH(3); //On the way to pikup you order


    public final int id;
    DeliveryType(int id){
        this.id = id;
    }


    public int value() {
        return id;
    }

    public static DeliveryType getDeliveryTypeById(Integer deliveryTypeId) {
        return Arrays.stream(DeliveryType.values())
                .filter(deliveryType -> deliveryType.id == deliveryTypeId)
                .findFirst()
                .orElse(null);
    }
}
