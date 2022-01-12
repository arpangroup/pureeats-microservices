package com.arpangroup.onboarding.entity;


import com.arpangroup.onboarding.common.DeliveryType;
import com.arpangroup.onboarding.common.StoreType;
import com.arpangroup.onboarding.entity.global.CityEntity;
import com.arpangroup.onboarding.entity.global.CuisineEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "stores")
//@Table(uniqueConstraints = {
//        @UniqueConstraint(name = "UniqueOwnerAndName", columnNames = {"owner", "name"})
//        //@UniqueConstraint(name = "UniqueOwnerAndContactNumber", columnNames = {"owner", "contact_number"})
//})
@Data
@NoArgsConstructor
@JsonPropertyOrder({
        "storeId", "storeOwnerId", "storeName", "description", "slug",
        "contactNumber", "fullAddress", "image", "rating",
        "storeType", "deliveryTypes",
        "priceRange", "averageCstForTwo", "deliveryTimeInMinute",
        "isPureVeg", "isPopular", "isRecommended", "isNotifiable", "autoacceptable", "isOpenTableSupport", "isTableReservationSupport",
        "isPermClosed", "isTempClosed", "isOpeningSoon",
        "licenceNumber", "disclaimer"
})
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String storeName;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "full_address", nullable = true)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String fullAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity storeLocation;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_details_id", nullable = false)
    private ContactDetailsEntity storeContactDetails;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_contact_details_id", nullable = false)
    private ContactDetailsEntity ownerContactDetails;

    @Column(name = "delivery_type", columnDefinition = "ENUM('DELIVERY', 'SELF_PICKUP', 'BOTH')", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "store_type", columnDefinition = "ENUM('RESTAURANT', 'GROCERY')", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType establishmentType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "store_cuisines",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "cuisine_id")}
    )
    private Set<CuisineEntity> cuisines = new HashSet<>();

    @Column(name = "operational", nullable = true)
    private String operational;

    @Column(name = "images", nullable = true)
    private String images;


    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pan_details_id", nullable = true)
    private PanEntity panDetails;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gst_details_id", nullable = true)
    private GstEntity gstDetails;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fssai_etails_id", nullable = true)
    private FssaiEntity fssaiDetails;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_details_id", nullable = true)
    private BankEntity bankDetails;

}
