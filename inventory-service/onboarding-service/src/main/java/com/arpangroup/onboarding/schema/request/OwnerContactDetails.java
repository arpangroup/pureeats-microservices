package com.arpangroup.onboarding.schema.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class OwnerContactDetails implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;

    @NotEmpty
    @Size(min = 4, max = 14, message = "{error.onboard.ownerContactDetails.ownerFullName.size}")
    @Pattern(regexp = "[a-zA-Z ]*$", message = "{error.onboard.ownerContactDetails.ownerFullName.pattern}")
    private String ownerFullName;

    @NotEmpty
    @Size(min = 10, max = 12, message = "{error.onboard.ownerContactDetails.ownerMobile.size}")
    @Pattern(regexp="(^$|[0-9]{10})", message = "{error.onboard.ownerContactDetails.ownerMobile.pattern}")
    private String ownerMobile;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$",  message = "{error.onboard.ownerContactDetails.ownerEmail.pattern}")
    private String ownerEmail;

    //private Boolean isEmailVerified;
    //private Boolean isMobileVerified;
}
