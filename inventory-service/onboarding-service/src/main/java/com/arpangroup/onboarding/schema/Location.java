package com.arpangroup.onboarding.schema;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Location {
    @NotNull(message = "lat should not be null")
    private Float lat;
    @NotNull(message = "lng should not be null")
    private Float lng;
}
