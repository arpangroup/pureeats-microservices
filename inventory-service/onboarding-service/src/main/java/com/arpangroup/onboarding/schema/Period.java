package com.arpangroup.onboarding.schema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Period {
    private int day;
    private long openTime;
    private long closeTime;

    public Period(int day, long openTime, long closeTime) {
        this.day = day;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
