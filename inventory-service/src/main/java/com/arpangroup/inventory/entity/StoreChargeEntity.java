package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.common.StoreChargeType;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "store_charges")
@Getter
@Setter
@NoArgsConstructor
@Audited
public class StoreChargeEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "store_charge_type", columnDefinition = "ENUM('FIXED', 'DYNAMIC', 'PERCENTAGE')", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreChargeType storeChargeType;

    private Float fixedStoreCharge;
    private Float fixedCommissionPercentage;

    private Float baseCommissionPercentage;
    private Float baseOrderAmount;
    private Float baseWeight;
    private Float extraCommissionPercentage;
    private Float extraOrderAmount;
    private Float extraWeight;


    private Date effectiveFrom;
    private Date effectiveTo;

    public static StoreChargeEntity fixedStoreCharge(Float fixedAmount) {
        StoreChargeEntity entity = new StoreChargeEntity();
        entity.setStoreChargeType(StoreChargeType.FIXED);
        entity.setFixedStoreCharge(fixedAmount);
        return entity;
    }

    public static StoreChargeEntity percentageCharge(Float commissionPercentage) {
        StoreChargeEntity entity = new StoreChargeEntity();
        entity.setStoreChargeType(StoreChargeType.PERCENTAGE);
        entity.setFixedCommissionPercentage(commissionPercentage);
        return entity;
    }

    public static StoreChargeEntity dynamicStoreCharge(Float baseCommissionPercentage, Float baseOrderAmount, Float extraCommissionPercentage, Float extraOrderAmount, Float baseWeight, Float extraWeight) {
        StoreChargeEntity entity = new StoreChargeEntity();
        entity.setStoreChargeType(StoreChargeType.DYNAMIC);
        entity.setBaseCommissionPercentage(baseCommissionPercentage);
        entity.setBaseOrderAmount(baseOrderAmount);
        entity.setExtraCommissionPercentage(extraCommissionPercentage);
        entity.setExtraOrderAmount(extraOrderAmount);
        entity.setBaseWeight(baseWeight);
        entity.setExtraWeight(extraWeight);
        return entity;
    }


}
