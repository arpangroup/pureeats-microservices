package com.arpangroup.onboarding.schema.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cuisines {
    private int cuisineId;
    private String cuisineName;

    public Cuisines(int cuisineId, String cuisineName) {
        this.cuisineId = cuisineId;
        this.cuisineName = cuisineName;
    }
}
