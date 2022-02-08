package com.arpangroup.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ContactInfo implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;
    private String fullName;
    private Long contactNumber;
    private Long email;
    private boolean isPhoneVerified;
    private boolean isEmailVerified;
    private boolean isNotifiable;

}
