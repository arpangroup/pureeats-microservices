package com.arpangroup.onboarding.controller;

import com.arpangroup.onboarding.mapper.EstablishmentMapper;
import com.arpangroup.onboarding.repository.CityRepository;
import com.arpangroup.onboarding.schema.response.Geocode;
import com.arpangroup.onboarding.schema.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@MyRestController
@RequestMapping("/geocode")
@Tag(name = "Geocode", description = "")
@Slf4j
public class GeocodeController extends BaseApiController{
    private final CityRepository repository;
    private final EstablishmentMapper mapper;

    public GeocodeController(CityRepository repository, EstablishmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Operation(summary = "Get location details based on coordinates", description = "Get Foodie and Nightlife Index, list of popular cuisines and nearby restaurants around the given coordinates.", tags = { "Geocode" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Geocode.class))),
            @ApiResponse(responseCode = "403", description = "invalid key")
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<Geocode>> findAll(
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lat", required=false) Float lat,
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lng", required=false) Float lng
    ) {
        List<Geocode> geoCodes = new ArrayList<>();
        if (lat != null && lng != null){
            log.info("Search geocode by LatLng: {{},{}}....", lat, lng);
            //entities = repository.findAllEstablishmentsByLatLng(lat, lng);
        }else{
            geoCodes = new ArrayList<>();
        }

        return ResponseEntity.ok(new ListResponse<>(geoCodes));
    }
}
