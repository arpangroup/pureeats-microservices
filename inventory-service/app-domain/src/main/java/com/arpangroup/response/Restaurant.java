package com.arpangroup.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder(value = {
        "id", "name",
        "operationalStatus", "hasOnlineDelivery", "isDeliveringNow",
        "storeType", "url", "location",
        "averageCostForTwo", "priceRange", "currency", "cuisines",
        "thumb", "photosUrl", "menuUrl", "featuredImage",
        "offers",
})
public class Restaurant {
    private int id = 21636;
    private String name = "Blue And Beyond";
    private String operationalStatus;
    private int hasOnlineDelivery = 1;
    private int isDeliveringNow = 1;

    private String storeType = "";
    private String url = "https://www.zomato.com/kolkata/blue-and-beyond-new-market-area?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1";
    private Location location;


    private Integer averageCostForTwo = 1600;
    private Integer priceRange = 3;
    private String currency = "Rs.";
    private String cuisines = "Continental, North Indian, Chinese, Italian, Beverages, Shake";

    private String thumb = "https://b.zmtcdn.com/data/pictures/6/21636/331b1af3e45988e38b1e8f8ccb312f62_featured_v2.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A";
    private String photosUrl = "https://www.zomato.com/kolkata/blue-and-beyond-new-market-area/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop";
    private String menuUrl = "https://www.zomato.com/kolkata/blue-and-beyond-new-market-area/menu?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1&openSwipeBox=menu&showMinimal=1#tabtop";
    private String featuredImage = "https://b.zmtcdn.com/data/pictures/6/21636/331b1af3e45988e38b1e8f8ccb312f62_featured_v2.jpg";

    private List<String> offers = new ArrayList<>();

}
