package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.common.DeliveryType;
import com.arpangroup.inventory.common.StoreType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "stores")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueOwnerAndName", columnNames = {"owner", "name"})
        //@UniqueConstraint(name = "UniqueOwnerAndContactNumber", columnNames = {"owner", "contact_number"})
})
@Data
@NoArgsConstructor
@Audited
@JsonPropertyOrder({
        "storeId", "storeOwnerId", "storeName", "description", "slug",
        "contactNumber", "fullAddress", "image", "rating",
        "storeType", "deliveryTypes",
        "priceRange", "averageCstForTwo", "deliveryTimeInMinute",
        "isPureVeg", "isPopular", "isRecommended", "isNotifiable", "autoacceptable", "isOpenTableSupport", "isTableReservationSupport",
        "isPermClosed", "isTempClosed", "isOpeningSoon",
        "licenceNumber", "disclaimer"
})
public class StoreEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer storeId;
    @Column(name = "owner", nullable = false)
    private int storeOwnerId;

    /*########################### common data [START] *######################*/
    @Column(name = "name", nullable = false)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String storeName;

    @Column(name = "description", nullable = true)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;

    @Column(name = "contact_number", nullable = false)
    private Long contactNumber;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;
    private String image;

    @Column(name = "min_order_price")
    private int minOrderPrice;

    @Column(name = "store_type", columnDefinition = "ENUM('RESTAURANT', 'GROCERY')", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_charge_id")
    private StoreChargeEntity storeCharge;
    /*########################### common data [END] *######################*/


    /*########################### operational area data [START] *######################*/
    @Column(name = "full_address", nullable = false)
    private String fullAddress;
    @Column(name = "pincode", nullable = true)
    private Integer pinCode;
    @Column(name = "landmark", nullable = true)
    private String landmark;
    @Column(name = "latitude", nullable = false)
    private Float latitude;
    @Column(name = "longitude", nullable = false)
    private Float longitude;
    @Column(name = "delivery_radius")
    private Integer deliveryRadiusInKm;
    /*########################### operational area data [END] *######################*/



    /*########################### meta data [START] *######################*/
    @Column(name = "rating")
    private float rating;
    @Column(name = "price_range")
    private Float priceRange;
    @Column(name = "average_cost_for_two")
    private Float averageCostForTwo;
    @Column(name = "delivery_time")
    private int deliveryTimeInMinute;
    @Column(name = "licence_number")
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String licenceNumber;
    @Column(name = "disclaimer")
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String disclaimer;
    /*########################### meta data [END] *######################*/


    /*########################### EXTRA data [START] *######################*/
    @Column(name = "is_pureveg", columnDefinition = "tinyint(1) default 0")
    private boolean isPureVeg;
    @Column(name = "is_popular", columnDefinition = "tinyint(1) default 0")
    private boolean isPopular;
    @Column(name = "is_recommended", columnDefinition = "tinyint(1) default 0")
    private boolean isRecommended;
    @Column(name = "is_notifiable", columnDefinition = "tinyint(1) default 0")
    private boolean isNotifiable;
    @Column(name = "autoacceptable", columnDefinition = "tinyint(1) default 0")
    private boolean autoacceptable;
    @Column(name = "open_table_support", columnDefinition = "tinyint(1) default 0")
    private boolean isOpenTableSupport;// NEW
    @Column(name = "is_table_reservation_support", columnDefinition = "tinyint(1) default 0")
    private boolean isTableReservationSupport;

    @Column(name = "is_perm_closed", columnDefinition = "tinyint(1) default 0")
    private boolean isPermClosed;
    @Column(name = "is_temp_closed", columnDefinition = "tinyint(1) default 0")
    private boolean isTempClosed;
    @Column(name = "is_opening_soon", columnDefinition = "tinyint(1) default 0")
    private boolean isOpeningSoon;
    /*########################### EXTRA data [END] *######################*/





    @Column(name = "delivery_type", columnDefinition = "ENUM('DELIVERY', 'SELF_PICKUP', 'BOTH')", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    @OneToMany(targetEntity = DeliveryChargeEntity.class)
    @JoinColumn(name = "store_id_fk", referencedColumnName = "id")
    private List<DeliveryChargeEntity> deliveryCharges;



    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_charge_id")
    private DeliveryChargeEntity deliveryCharge;



    //private Address address;
}
