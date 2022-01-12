package com.arpangroup.onboarding.schema.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {"locality", "nearbyRestaurants"})
public class Geocode {
    private Locality locality;
    private List<Restaurant> nearbyRestaurants;
}
