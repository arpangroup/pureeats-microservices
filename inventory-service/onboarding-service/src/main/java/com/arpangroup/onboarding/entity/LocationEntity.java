package com.arpangroup.onboarding.entity;

import com.arpangroup.onboarding.common.AccountType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity(name = "location_details")
@Data
@Audited
@NoArgsConstructor
@JsonPropertyOrder(value = {"address", "locality", "landmark", "city", "city_id", "latitude", "longitude", "zipcode", "country_id", "locality_verbose"})
public class LocationEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "lat", nullable = false)
    @NotNull
    @Min(value = -90, message = "{error.onboard.storeLocation.lat.min}")
    @Max(value = 90, message = "{error.onboard.storeLocation.lat.max}")
    private Float lat;

    @Column(name = "lng", nullable = false)
    @NotNull
    @Min(value = -180, message ="{error.onboard.storeLocation.lng.min}")
    @Max(value = 180, message = "{error.onboard.storeLocation.lng.max}")
    private Float lng;

    @Column(name = "city", nullable = false)
    @NotEmpty
    @Size(min = 2, max = 50, message="{error.onboard.storeName.size}")
    @Pattern(regexp = "[a-zA-Z ]*$", message = "{error.onboard.storeName.pattern}")
    private String city;

    @Column(name = "city_id", nullable = false, columnDefinition = "integer default 0")
    private Integer cityId;


    @Column(name = "region_code", nullable = true)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String regionCode;

    @Column(name = "region_name", nullable = true)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String regionName;

    @Column(name = "country_code", nullable = true)
    @Size(min = 2, max = 4, message="{error.onboard.storeLocation.countryCode.size}")
    @Pattern(regexp="^[A-Za-z0-9+]*$", message = "{error.onboard.storeLocation.countryCode.pattern}")
    private String countryCode;

    @Column(name = "country_name", nullable = true)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String countryName;

    @Column(name = "zip", nullable = false)
    @NotNull
    @Min(100000)
    @Max(999999)
    private Integer zip;

    @Column(name = "address", nullable = false)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String address;

    @Column(name = "landmark", nullable = true)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String landmark;

    @Column(name = "locality", nullable = true)
    @Pattern(regexp="^[A-Za-z \\s\\-]*$")
    private String locality;

    //private String timezone;
    //private String isp;
    //private String ip;

    @Column(name = "delivery_radius", nullable = true)
    private int deliveryRadius;

}
