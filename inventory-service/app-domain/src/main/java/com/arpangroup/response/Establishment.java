package com.arpangroup.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "name"})
public class Establishment implements Serializable {
    private static final long serialVersionUID = 1369279224740734441L;

    private Integer id;
    private String name;
}
