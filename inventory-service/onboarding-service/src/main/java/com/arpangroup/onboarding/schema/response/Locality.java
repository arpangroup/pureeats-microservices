package com.arpangroup.onboarding.schema.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {
        "address","entityType", "entityId", "title", "latitude", "longitude", "cityId", "cityName", "countryId", "countryName"
})
public class Locality {
    private String entityType;
    private int entityId;
    private String title;
    private Float latitude;
    private Float longitude;
    private int cityId;
    private String cityName;
    private int countryId;
    private String countryName;
}
