package com.arpangroup.inventory.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class ListResponse<T> {
    private int count;
    private String message;
    private List<T> data;

    private ListResponse(){//important
        // private constructor to avoid null object creation
    }

    public ListResponse(List<T> data) {
        this.data = data != null ? data : new ArrayList<>();
        this.count = data != null ? data.size() : 0;
        this.message = CollectionUtils.isEmpty(data) ? "No records found" : data.size() + " records found";
    }
}
