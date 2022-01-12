package com.arpangroup.inventory.dto.response.store;

import com.arpangroup.inventory.common.DeliveryType;
import com.arpangroup.inventory.common.StoreType;
import com.arpangroup.inventory.dto.request.DeliveryCharge;
import com.arpangroup.inventory.dto.request.StoreCharge;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({
        "storeId", "storeName", "description", "slug", "image", "images",
        "contactNumber", "minOrderPrice", "storeType", "storeCharge",
        "fullAddress", "pinCode", "landmark", "latitude", "longitude", "deliveryRadiusInKm",
        "deliveryTypes",
        "priceRange", "averageCostForTwo", "deliveryTimeInMinute",
        "isPureVeg", "isPopular", "isRecommended", "isNotifiable", "autoacceptable", "isOpenTableSupport", "isTableReservationSupport",
        "isPermClosed", "isTempClosed", "isOpeningSoon",
        "licenceNumber", "disclaimer",
        "delivery_time", "rating", "location", "timing", "coupons", "items"
})
@Getter
@Setter
@NoArgsConstructor
public class StoreResponse {
    @Schema(description = "unique id of the store.", example = "583945", required = true)
    private int storeId;
    @Schema(description = "Name of the store.", example = "PureEats Restaurant", required = true)
    private String storeName;
    @Schema(description = "description about the store", example = "Pure Eats Store - A nice awesome Restaurant", required = false)
    private String description;
    @Schema(description = "unique slug name of the store", example = "pureeats-restaurant-k62gqi7ar8tw4zq", required = false)
    private String slug;
    @Schema(description = "image URLs of the store.", example = "[\"https://picsum.photos/1280/720?random=1\"]", required = false)
    private String image;
    @Schema(description = "contactNumber with country code of the store/storeOwner", example = "918123456789", required = false)
    private Long contactNumber;
    @Schema(description = "type of the store. ", example = "RESTAURANT", required = true)
    private StoreType storeType;
    private StoreCharge storeCharge;

    @Schema(description = "full address of the store", example = "Mountain View, California, United States 282001")
    private String fullAddress;
    @Schema(description = "PIN code of the store. ", example = "700001", nullable = true)
    private Integer pinCode;
    @Schema(description = "landmark of the store. ", example = "Near Google Headquarters", nullable = true)
    private String landmark;
    @Schema(description = "latitude of the store. ", example = "22.685935047338873", nullable = false)
    private Float latitude;
    @Column(name = "longitude", nullable = false)
    private Float longitude;
    @Schema(description = "Delivery Radius in Km ", example = "60", nullable = true)
    private int deliveryRadiusInKm;


    //@Schema(description = "delivery type of the store.", example = "SELF_PICKUP", required = true)
    //private DeliveryType deliveryType;
    @Schema(description = "delivery type support for this store.", example = "[\"SELF_PICKUP\", \"DELIVERY\"]", required = true)
    private List<DeliveryType> deliveryTypes;

    @Schema(description = "price range of the store. ", example = "100", nullable = true)
    private Float priceRange;
    @Schema(description = "average cost for two items of the store. ", example = " ", nullable = true)
    private Float averageCostForTwo;
    @Schema(description = "average delivery time of the store. ", example = " ", nullable = true)
    private Integer deliveryTimeInMinute;


    private boolean isPureVeg;
    private boolean isPopular;
    private boolean isRecommended;
    private boolean isNotifiable;
    private boolean isOpenTableSupport;
    private boolean isTableReservationSupport;
    private boolean isPermClosed;
    private boolean isTempClosed;
    private boolean isOpeningSoon;


    @Schema(description = "Certificate/License Code of the store", example = "WB1234567", required = false)
    private String licenceNumber;
    @Schema(description = "disclaimer of the store. ", example = " ")
    private String disclaimer;


    @Schema(description = "cuisines of the store", example = " ", required = false)
    private String cuisine;

    private DeliveryCharge deliveryCharge;


}
