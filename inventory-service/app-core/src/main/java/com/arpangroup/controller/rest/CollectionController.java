package com.arpangroup.controller.rest;

import com.arpangroup.response.Collection;
import com.arpangroup.response.ListResponse;
import com.arpangroup.entity.master.CollectionEntity;
import com.arpangroup.mapper.CollectionMapper;
import com.arpangroup.repository.CityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections")
@Tag(name = "Collections", description = "")
@Slf4j
public class CollectionController extends BaseApiController{
    private final CityRepository repository;
    private final CollectionMapper mapper;

    public CollectionController(CityRepository repository, CollectionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Operation(summary = "Returns PureEats Restaurant collections in a city", description = "List of all restaurants listed in any particular Zomato Collection can be obtained using the '/search' API with Collection ID and Zomato City ID as the input.", tags = { "Collections" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Collection.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "invalid key")
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<Collection>> findAll(
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lat", required=false) Float lat,
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lng", required=false) Float lng,
            @Parameter(description="id of the city for which collections are needed") @RequestParam(value = "city_id", required=false) Integer cityId,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int page
    ) {
        List<CollectionEntity> collectionList;

        if (cityId != null){
            log.info("Search city by cityIds: {}....", cityId);
            collectionList = repository.findAllCollectionsByCityId(cityId);
        }else if (lat != null && lng != null){
            log.info("Search collection by LatLng: {{},{}}....", lat, lng);
            collectionList = repository.findAllCollectionsByLatLng(lat, lng);
        }else{
            collectionList = new ArrayList<>();
        }

        List<Collection> collections = collectionList.stream().map(mapper::mapTo).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(collections));
    }
}
