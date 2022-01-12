package com.arpangroup.onboarding.schema;

import com.arpangroup.onboarding.common.DeliveryType;
import com.arpangroup.onboarding.common.StoreType;
import com.arpangroup.onboarding.schema.request.OwnerContactDetails;
import com.arpangroup.onboarding.schema.request.StoreContactDetails;
import com.arpangroup.onboarding.schema.request.StoreLocation;
import com.arpangroup.onboarding.validator.EnumValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class OnboardRequest implements Serializable {
    private static final long serialVersionUID = 1123459224740734441L;

    @Schema(description = "name of the store. ", example = "PureEats Restaurant", required = true, nullable = false)
    @NotEmpty
    @Size(min = 5, max = 100, message="{error.onboard.storeName.size}")
    @Pattern(regexp = "[a-zA-Z ]*$", message = "{error.onboard.storeName.pattern}")
    private String storeName;


    @Schema(description = "address of the store. ", example = "Mountain View, California, United States 282001", required = true, nullable = false)
    @NotEmpty
    @Size(min = 5, max = 100, message = "{error.onboard.fullAddress.size}")
    @Pattern(regexp = "^[a-zA-Z0-9.,\\-\\/+=_ ]*$", message = "{error.onboard.fullAddress.pattern}")
    private String fullAddress;

    @Schema(description = "location details of the store. ", example = "", required = true, nullable = false)
    @NotNull
    @Valid
    private StoreLocation storeLocation;


    @Schema(description = "contact details of the store. ", example = "", required = true, nullable = false)
    @NotNull
    @Valid
    private StoreContactDetails storeContactDetails;


    @Schema(description = "owner contact details of the store. ", example = "", required = true, nullable = false)
    @NotNull
    @Valid
    private OwnerContactDetails ownerContactDetails;


    @Schema(description = "DeliveryType like DELIVERY or SELF_PICKUP or BOTH ", example = "RESTAURANT", required = true, nullable = false)
    @NotEmpty(message = "{error.onboard.deliveryType.empty}")
    @EnumValidator(enumClazz = DeliveryType.class, message = "{error.onboard.deliveryType.invalid}")
    private String deliveryType;


    @Schema(description = "Establishment like RESTAURANT or GROCERY ", example = "RESTAURANT", nullable = true)
    @NotEmpty(message = "{error.onboard.establishmentType.empty}")
    @EnumValidator(enumClazz = StoreType.class, message = "{error.onboard.establishmentType.invalid}")
    private String establishmentType;

   /*
    @Schema(description = "cuisines like Biryani or Chinese ", example = "Chinese", nullable = true)
    private String[] cuisines;

     */



//    private Timing[] operationalDetails;
//    private String[] menuImages;

}
