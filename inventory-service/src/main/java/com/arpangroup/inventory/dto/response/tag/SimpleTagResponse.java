package com.arpangroup.inventory.dto.response.tag;

import com.arpangroup.inventory.dto.response.product.SimpleProductResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"tagName"})
@Getter
@Setter
@NoArgsConstructor
public class SimpleTagResponse {
    @Schema(description = "Name of the tag.", example = "SPICY", required = true)
    @NotBlank
    private String tagName;
}
