package com.arpangroup.onboarding.schema.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StoreLocation implements Serializable {
    private static final long serialVersionUID = 136927922474041L;

    @NotNull
    @Min(value = -90, message = "{error.onboard.storeLocation.lat.min}")
    @Max(value = 90, message = "{error.onboard.storeLocation.lat.max}")
    private Float lat;

    @NotNull
    @Min(value = -180, message ="{error.onboard.storeLocation.lng.min}")
    @Max(value = 180, message = "{error.onboard.storeLocation.lng.max}")
    private Float lng;

    @NotEmpty
    @Size(min = 2, max = 50, message="{error.onboard.storeName.size}")
    @Pattern(regexp = "[a-zA-Z ]*$", message = "{error.onboard.storeName.pattern}")
    private String city;


    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String regionCode;

    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String regionName;


    @Size(min = 2, max = 4, message="{error.onboard.storeLocation.countryCode.size}")
    @Pattern(regexp="^[A-Za-z0-9+]*$", message = "{error.onboard.storeLocation.countryCode.pattern}")
    private String countryCode;

    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String countryName;

    @NotNull
    @Min(100000)
    @Max(999999)
    private Integer zip;

    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String landmark;

    //private String timezone;
    //private String isp;
    //private String ip;


    private Integer deliveryRadius;

}
