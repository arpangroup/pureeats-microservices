package com.arpangroup.onboarding.schema;

import com.arpangroup.onboarding.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OnlineOnboardRequestExt extends OnboardRequest{
//    private String description; // provided in webPage
//    private Integer deliveryRadiusInKm; // provided in storeLocation
//    private String businessStatus;//only for response/output

    private PanEntity panDetails;
    private GstEntity gstDetails;
    private FssaiEntity fssaiDetails;
    private BankEntity bankEntity;

    private EstablishmentWebPage webPage;
}
