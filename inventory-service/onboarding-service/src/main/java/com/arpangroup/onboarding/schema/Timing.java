package com.arpangroup.onboarding.schema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Timing {
    private String day;
    private String openTime;
    private String closeTime;

    public Timing(String day, String openTime, String closeTime) {
        this.day = day;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
