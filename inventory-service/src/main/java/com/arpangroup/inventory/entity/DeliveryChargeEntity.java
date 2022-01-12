package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.common.DeliveryChargeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "delivery_charges")
@Getter
@Setter
@NoArgsConstructor
@Audited
public class DeliveryChargeEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "delivery_charge_type", columnDefinition = "ENUM('FIXED', 'DYNAMIC')", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryChargeType deliveryChargeType;

    private Float fixedDeliveryCharge;

    private Float baseDeliveryCharge;
    private Integer baseDeliveryDistance;
    private Float extraDeliveryCharge;
    private Integer extraDeliveryDistance;


    private Date effectiveFrom;
    private Date effectiveTo;

    public static DeliveryChargeEntity fixedDeliveryCharge(Float fixedDeliveryCharge) {
        DeliveryChargeEntity entity = new DeliveryChargeEntity();
        entity.setDeliveryChargeType(DeliveryChargeType.FIXED);
        entity.setFixedDeliveryCharge(fixedDeliveryCharge);
        return entity;
    }

    public static DeliveryChargeEntity dynamicDeliveryCharge(Float baseDeliveryCharge, Integer baseDeliveryDistance, Float extraDeliveryCharge, Integer extraDeliveryDistance) {
        DeliveryChargeEntity entity = new DeliveryChargeEntity();
        entity.setDeliveryChargeType(DeliveryChargeType.DYNAMIC);
        entity.setBaseDeliveryCharge(baseDeliveryCharge);
        entity.setBaseDeliveryDistance(baseDeliveryDistance);
        entity.setExtraDeliveryCharge(extraDeliveryCharge);
        entity.setExtraDeliveryDistance(extraDeliveryDistance);
        return entity;
    }

}
