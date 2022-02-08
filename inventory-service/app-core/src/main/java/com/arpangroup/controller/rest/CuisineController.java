package com.arpangroup.controller.rest;

import com.arpangroup.response.Cuisines;
import com.arpangroup.response.ListResponse;
import com.arpangroup.entity.master.CuisineEntity;
import com.arpangroup.mapper.CuisineMapper;
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


@RequestMapping("/cuisines")
@Tag(name = "Cuisines", description = "")
@Slf4j
public class CuisineController extends BaseApiController{
    private final CityRepository repository;
    private final CuisineMapper mapper;

    public CuisineController(CityRepository repository, CuisineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Operation(summary = "Get list of all cuisines of restaurants listed in a city", description = "List of all restaurants serving a particular cuisine can be obtained using '/search' API with cuisine ID and location details.", tags = { "Cuisines" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cuisines.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "invalid key")
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<Cuisines>> findAll(
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lat", required=false) Float lat,
            @Parameter(description="latitude / longitude of any point within a city") @RequestParam(value = "lng", required=false) Float lng,
            @Parameter(description="id of the city for which cuisines are needed") @RequestParam(value = "city_id", required=false) Integer cityId
    ) {
        List<CuisineEntity> entities;
        if (cityId != null){
            log.info("Search cuisine by cityId: {}....", cityId);
            entities = repository.findAllCuisineByCityId(cityId);
        }else if (lat != null && lng != null){
            log.info("Search cuisines by LatLng: {{},{}}....", lat, lng);
            entities = repository.findAllCuisinesByLatLng(lat, lng);
        }else{
            entities = new ArrayList<>();
        }

        List<Cuisines> cuisines = entities.stream().map(mapper::mapTo).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(cuisines));
    }
}
