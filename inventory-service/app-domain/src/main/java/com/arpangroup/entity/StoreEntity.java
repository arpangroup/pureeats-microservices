package com.arpangroup.entity;


import com.arpangroup.entity.master.CuisineEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "store_cuisines",
            joinColumns = {@JoinColumn(name = "store_id")},
            inverseJoinColumns = {@JoinColumn(name = "cuisine_id")}
    )
    private Set<CuisineEntity> cuisines = new HashSet<>();

}
