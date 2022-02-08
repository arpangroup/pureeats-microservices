package com.arpangroup.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class ListResponse<T> {
    private String status;
    private int count;
    private String message;
    private List<T> data;

//    private int hasMore;
//    private int hasTotal;

    private ListResponse(){//important
        // private constructor to avoid null object creation
    }

    public ListResponse(List<T> data) {
        this.status = "success";
        this.data = data != null ? data : new ArrayList<>();
        this.count = data != null ? data.size() : 0;
        this.message = CollectionUtils.isEmpty(data) ? "No records found" : data.size() + " records found";
    }
}
