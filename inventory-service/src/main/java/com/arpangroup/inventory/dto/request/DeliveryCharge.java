package com.arpangroup.inventory.dto.request;

import com.arpangroup.inventory.common.DeliveryChargeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryCharge {
    @NotEmpty(message = "deliveryChargeType should not be empty or null")
    private String deliveryChargeType;

    private Float fixedDeliveryCharge;

    private Float baseDeliveryCharge;
    private Integer baseDeliveryDistance;
    private Float extraDeliveryCharge;
    private Integer extraDeliveryDistance;


}
