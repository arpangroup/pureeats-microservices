package com.arpangroup.onboarding.schema.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"collectionId", "title", "description", "resCount", "imageUrl"})
public class Collection {
    private int collectionId;
    private String title;
    private String description;
    private int resCount;
    private String imageUrl;
}
