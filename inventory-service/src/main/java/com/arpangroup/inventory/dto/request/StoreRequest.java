package com.arpangroup.inventory.dto.request;

import com.arpangroup.inventory.entity.DeliveryChargeEntity;
import com.arpangroup.inventory.entity.StoreChargeEntity;
import com.arpangroup.inventory.validator.annotation.PhoneNumberConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class StoreRequest {
    @Schema(description = "id of the store owner. ", example = "875689", required = true)
//    @JsonProperty("store_owner_id")
    @Min(value = 1, message = "should not be negative")
    private int storeOwnerId;

    @Schema(description = "name of the store. ", example = "PureEats Restaurant", required = true)
    @NotEmpty(message = "should not be empty or null")
    @NotEmpty(message = "storeName should not be empty or null")
    @Length(min = 3, max = 40, message = "should be 3-40 characters long")
//    @JsonProperty("store_name")
    //@Size(max = 100)
    private String storeName;
    @Schema(description = "description of the store. ", example = " ", nullable = true)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;
    @Schema(description = "contactNumber of the store. ", example = " ", nullable = true)
//    @NotNull(message = "contactNumber should not be empty")
    @PhoneNumberConstraint(minLength = 10, maxLength = 12, nullable = false)
    private String contactNumber;
    @Schema(description = "minimum order price of the store. ", example = "250", nullable = true)
    private int minOrderPrice;
    @Schema(description = "Store Type like RESTAURANT or GROCERY ", example = "RESTAURANT", nullable = true)
    private String storeType;

    @Schema(description = "address of the store. ", example = "Mountain View, California, United States 282001", nullable = false)
    @Size(min = 5, max = 100, message = "should be minimum 5 character and maximum 100 characters long")
    @NotEmpty(message = "fullAddress should not be empty or null")
    private String fullAddress;
    @Schema(description = "PIN code of the store. ", example = "700001", nullable = true)
    private Integer pinCode;
    @Schema(description = "landmark of the store. ", example = "Near Google Headquarters", nullable = true)
    private String landmark;
    @Schema(description = "latitude of the store. ", example = "22.685935047338873", nullable = false)
    @NotNull(message = "latitude should not be null")
    private Float latitude;
    @Schema(description = "longitude of the store. ", example = "88.34287695586681", nullable = false)
    @NotNull(message = "longitude should not be null")
    private Float longitude;
    @Schema(description = "Delivery Radius in Km ", example = "60", nullable = true)
    private Integer deliveryRadiusInKm;



    @Schema(description = "contactNumber of the store. ", example = " ")
    private Integer deliveryType;


    @Schema(description = "default rating of the store. ", example = "3.5", nullable = true)
    private Float rating;
    @Schema(description = "price range of the store. ", example = "200", nullable = true)
    private Float priceRange;
    @Schema(description = "average cost of the store. ", example = "190", nullable = true)
    private Float averageCostForTwo;
    @Schema(description = "average delivery time of the store. ", example = "20", nullable = true)
    private Integer deliveryTimeInMinute;
    @Schema(description = "Certificate/License Code of the store", example = "WB1234567", nullable = true)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String licenceNumber;
    @Schema(description = "disclaimer of the store. ", example = " ")
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String disclaimer;

    private boolean isPureVeg;
    private boolean isPopular;
    private boolean isRecommended;
    private boolean isNotifiable;
    private boolean autoacceptable;
    private boolean isOpenTableSupport;
    private boolean isTableReservationSupport;
    private boolean isPermClosed;
    private boolean isTempClosed;
    private boolean isOpeningSoon;


    @NotNull(message = "storeCharge should not be null")
    @Valid
    private StoreCharge storeCharge;

    @NotNull(message = "deliveryCharge should not be null")
    @Valid
    private DeliveryCharge deliveryCharge;

}
