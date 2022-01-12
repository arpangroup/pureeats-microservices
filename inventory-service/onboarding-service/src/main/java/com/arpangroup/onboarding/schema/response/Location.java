package com.arpangroup.onboarding.schema.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {
        "address","locality", "city", "latitude", "longitude", "longitude", "zipcode", "cityName", "countryId", "countryName"
})
public class Location {
    private String address;
    private String locality;
    private String city;
    private Float latitude;
    private Float longitude;
    private String zipcode;
    private String cityName;
    private int countryId;
}
