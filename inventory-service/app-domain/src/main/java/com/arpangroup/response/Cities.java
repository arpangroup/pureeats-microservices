package com.arpangroup.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.annotation.Documented;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name"})
public class Cities implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;
    @Schema(example = "1")
    private Integer id;
    @Schema(example = "Kolkata")
    private String name;
}
