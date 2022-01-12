package com.arpangroup.onboarding.controller;

import com.arpangroup.onboarding.entity.global.CityEntity;
import com.arpangroup.onboarding.mapper.CityMapper;
import com.arpangroup.onboarding.repository.CityRepository;
import com.arpangroup.onboarding.schema.response.Cities;
import com.arpangroup.onboarding.schema.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
@Tag(name = "Cities", description = "")
@Slf4j
public class CityController extends BaseApiController{
    private final CityRepository repository;
    private final CityMapper mapper;

    public CityController(CityRepository repository, CityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Operation(summary = "Find the city details for a city", description = "", tags = { "Cities" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cities.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "invalid key")
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<Cities>> findAll(
            @Parameter(description="query by city name") @RequestParam(value = "q", required = false) String q,
            @Parameter(description="latitude") @RequestParam(value = "lat", required=false) Float lat,
            @Parameter(description="longitude") @RequestParam(value = "lng", required=false) Float lng,
            @Parameter(description="comma separated city_id values") @RequestParam(value = "city_ids", required=false) String cityIds,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int page
    ) {
        List<CityEntity> cityList;

        if (StringUtils.isNotEmpty(q)){
            log.info("Search city by query: {}....", q);
            cityList = repository.findAllCityByCityName(q);
        }else if (StringUtils.isNotEmpty(cityIds)){
            log.info("Search city by cityIds: {}....", cityIds);
            List<Integer> ids = Arrays.stream(cityIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            cityList = repository.findAllCityByCityIds(ids);
        }else if (lat != null && lng != null){
            log.info("Search city by LatLng: {{},{}}....", lat, lng);
            cityList = repository.findAllCityByLatLng(lat, lng);
        }else{
            cityList = new ArrayList<>();
        }

        List<Cities> cities = cityList.stream().map(mapper::mapTo).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(cities));
    }
}
