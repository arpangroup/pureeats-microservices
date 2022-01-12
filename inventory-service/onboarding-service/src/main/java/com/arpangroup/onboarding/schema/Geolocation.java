package com.arpangroup.onboarding.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Deprecated
//https://developers.google.com/maps/documentation/geolocation/overview
public class Geolocation {
    @Schema(description = "The mobile country code (MCC) for the device's home network. Note: Supported for radioType gsm (default), wcdma, lte and nr; not used for cdma. Valid range: 0–999",  example = "310", required = true)
    @NotNull
    private Long homeMobileCountryCode;

    @Schema(description = "The Mobile Network Code for the device's home network. This is the MNC for GSM, WCDMA, LTE and NR. CDMA uses the System ID (SID)",  example = "410", required = true)
    @NotNull
    private Long homeMobileNetworkCode;

    @Schema(description = "The mobile radio type. Supported values are gsm, cdma, wcdma, lte and nr.",  example = "gsm", required = true)
    @NotNull
    private String radioType;

    @Schema(description = "The carrier name.",  example = "Vodafone", required = true)
    @NotNull
    private String carrier;

    @Schema(description = "Specifies whether to fall back to IP geolocation if WiFi and cell tower signals are missing, empty, or not sufficient to estimate device location.",  example = "true", required = true)
    @NotNull
    private boolean considerIp;

    @Schema(description = "An array of cell tower objects",  example = "-60", required = true)
    @NotNull
    private List<CellTower> cellTowers;

    @Schema(description = "An array of WiFi access point objects.",  example = "-60", required = true)
    @NotNull
    private List<WiFiAccessPoint> wifiAccessPoints;
}
