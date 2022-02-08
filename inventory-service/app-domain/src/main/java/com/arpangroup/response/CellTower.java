package com.arpangroup.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@Deprecated
//https://developers.google.com/maps/documentation/geolocation/overview
public class CellTower {
    @Schema(description = "Unique identifier of the cell. \nNote: Required for radioType gsm (default), cdma, wcdma and lte; rejected for nr. See the Calculating cellId section below, which also lists the valid value ranges for each radio type.", example = "170402199", required = true)
    @NotNull
    private Long cellId;

    @Schema(description = "The Location Area Code (LAC) for GSM and WCDMA networks. The Network ID (NID) for CDMA networks. The Tracking Area Code (TAC) for LTE and NR networks.",  example = "35632", required = true)
    @NotNull
    private Long locationAreaCode;

    @Schema(description = "The cell tower's Mobile Country Code (MCC).",  example = "310", required = true)
    @NotNull
    private Integer mobileCountryCode;

    @Schema(description = "The cell tower's Mobile Network Code. This is the MNC for GSM, WCDMA, LTE and NR. CDMA uses the System ID (SID).",  example = "410", required = true)
    @NotNull
    private Integer mobileNetworkCode;

    @Schema(description = "Radio signal strength measured in dBm.",  example = "-60", required = false)
    @NotNull
    private Float signalStrength;
}
