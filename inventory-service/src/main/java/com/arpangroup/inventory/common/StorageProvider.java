package com.arpangroup.inventory.common;

import java.util.Arrays;

public enum StorageProvider {
    FILE("FILE"),
    S3("S3");


    private final String value;
    StorageProvider(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }

}
