package com.arpangroup.onboarding.common;

import java.util.Arrays;

public enum EntityType {
    ESTABLISHMENT("ESTABLISHMENT"),
    ESTABLISHMENT_OWNER("ESTABLISHMENT_OWNER"),
    USER("USER");


    private final String value;
    EntityType(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }


    public static EntityType getEntityType(String entityType) {
        return Arrays.stream(EntityType.values())
                .filter(e -> e.value.equals(entityType))
                .findFirst()
                .orElse(null);
    }
}
