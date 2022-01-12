package com.arpangroup.onboarding.schema.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StoreContactDetails implements Serializable {
    private static final long serialVersionUID = 13224740734441L;

    @NotNull
    @NotEmpty
    @Size(min=10,max=10, message = "{error.onboard.storeContactDetails.phone.size}")
    @Pattern(regexp="(^$|[0-9]{10})", message = "${error.onboard.storeContactDetails.phone.pattern}")
    private String phone;

    @Size(min=10,max=12, message = "{error.onboard.storeContactDetails.landline.size}")
    @Pattern(regexp = "[0-9]", message = "{error.onboard.storeContactDetails.landline.pattern}")
    private String landline;
    //private Boolean isPhoneVerified;
    //private Boolean isEmailVerified;
}
