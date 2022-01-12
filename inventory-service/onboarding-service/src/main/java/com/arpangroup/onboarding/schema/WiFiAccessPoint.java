package com.arpangroup.onboarding.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Deprecated
//https://developers.google.com/maps/documentation/geolocation/overview
public class WiFiAccessPoint {
    @Schema(description = "The MAC address of the WiFi node. It's typically called a BSS, BSSID or MAC address. \n Required. : (colon) separated hexadecimal string.",  example = "9c:1c:12:b0:45:f1", required = true)
    @NotNull(message = "should not be null or empty, Required. : (colon) separated hexadecimal string.")
    private String macAddress;
    @Schema(description = "The current signal strength measured in dBm.",  example = "-43", required = true)
    @NotNull
    private Float signalStrength;
    @Schema(description = "The current signal to noise ratio measured in dB.",  example = "0", required = true)
    @NotNull
    private Float signalToNoiseRatio;
    @Schema(description = "The channel over which the client is communicating with the access point.\t",  example = "11", required = true)
    @NotNull
    private Integer channel;
}
