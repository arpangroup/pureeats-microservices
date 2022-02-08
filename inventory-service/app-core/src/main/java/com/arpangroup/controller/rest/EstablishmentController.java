package com.arpangroup.controller.rest;

import com.arpangroup.response.Establishment;
import com.arpangroup.response.ListResponse;
import com.arpangroup.entity.master.EstablishmentEntity;
import com.arpangroup.mapper.EstablishmentMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/establishments")
@Tag(name = "Establishments", description = "")
@Slf4j
public class EstablishmentController extends BaseApiController{
    private final CityRepository repository;
    private final EstablishmentMapper mapper;

    public EstablishmentController(CityRepository repository, EstablishmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Operation(summary = "Get list of restaurant types in a city", description = "List of all restaurants categorized under a particular restaurant type can obtained using /Search API with Establishment ID and location details as inputs.", tags = { "Establishments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Establishment.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "invalid key")
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<Establishment>> findAll(
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lat", required=false) Float lat,
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lng", required=false) Float lng,
            @Parameter(description="id of the city for which cuisines are needed") @RequestParam(value = "city_id", required=false) Integer cityId
    ) {
        List<EstablishmentEntity> entities;
        if (cityId != null){
            log.info("Search cuisine by cityId: {}....", cityId);
            entities = repository.findAllEstablishmentsByCityId(cityId);
        }else if (lat != null && lng != null){
            log.info("Search cuisines by LatLng: {{},{}}....", lat, lng);
            entities = repository.findAllEstablishmentsByLatLng(lat, lng);
        }else{
            entities = new ArrayList<>();
        }

        List<Establishment> cuisines = entities.stream().map(mapper::mapTo).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(cuisines));
    }
}
