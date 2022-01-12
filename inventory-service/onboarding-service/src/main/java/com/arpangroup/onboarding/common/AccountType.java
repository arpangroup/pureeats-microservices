package com.arpangroup.onboarding.common;

import java.util.Arrays;

public enum AccountType {
    SAVING("SAVING"),
    CURRENT("CURRENT"),;


    private final String value;
    AccountType(String value){
        this.value = value;
    }


    public String value() {
        return value;
    }


    public static AccountType getAccountType(String accountType) {
        return Arrays.stream(AccountType.values())
                .filter(a -> a.value.equals(accountType))
                .findFirst()
                .orElse(null);
    }
}
