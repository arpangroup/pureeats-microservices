package com.arpangroup.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
//@ApiMode(value = "Car", description = "The model for car")
public class TagRequest {
    @Schema(description = "name of the tag. ", example = "SPICY", required = true)
    @JsonProperty("tag_name")
    @NotEmpty(message = "should not be empty or null")
    @Length(min = 3, max = 40, message = "should be 3-40 characters long")
    //@Size(max = 100)
    private String tagName;

    @Schema(description = "description of the TAG. ", example = " ", required = false)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;

//    @Schema(description = "Phone number of the contact.", example = "62482211", required = false)
//    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
//    @Size(max = 25)
//    private String phone;


//    @Schema(description = "Email address of the contact.", example = "jessica@ngilang.com", required = false)
//    @Email(message = "Email Address")
//    @Size(max = 100)
//    private String email;
}
