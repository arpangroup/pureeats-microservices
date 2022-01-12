package com.arpangroup.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
//@ApiMode(value = "Car", description = "The model for car")
public class CategoryRequestDto {
    @Schema(description = "name of the category. ", example = "chicken", required = true)
    @JsonProperty("category_name")
    @NotEmpty(message = "should not be empty or null")
    @Length(min = 3, max = 40, message = "should be 3-40 characters long")
    //@Size(max = 100)
    private String categoryName;

    @Schema(description = "description of the category. ", example = " ", required = false)
    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;

    @Schema(description = "image of the category. ", example = " ", nullable = true, required = false)
    private String image;

    @Schema(description = "description of the category. ", required = false)
    private Boolean isActive;



//    @Schema(description = "Phone number of the contact.", example = "62482211", required = false)
//    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
//    @Size(max = 25)
//    private String phone;


//    @Schema(description = "Email address of the contact.", example = "jessica@ngilang.com", required = false)
//    @Email(message = "Email Address")
//    @Size(max = 100)
//    private String email;
}
