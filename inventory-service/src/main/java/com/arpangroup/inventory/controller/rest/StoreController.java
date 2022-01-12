package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.dto.request.StoreRequest;
import com.arpangroup.inventory.dto.response.ListResponse;
import com.arpangroup.inventory.dto.response.store.StoreResponse;
import com.arpangroup.inventory.entity.StoreEntity;
import com.arpangroup.inventory.exception.ApiError;
import com.arpangroup.inventory.mapper.StoreMapper;
import com.arpangroup.inventory.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.arpangroup.inventory.controller.rest.BaseApiController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/stores")
@Tag(name = "stores", description = "the store API")
public class StoreController extends BaseApiController{
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
    private final StoreService storeService;
    private final StoreMapper mapper;

    @Value("${storage.provider:FILE}")
    private String storageProvider;

    public StoreController(StoreService storeService, StoreMapper mapper) {
        this.storeService = storeService;
        this.mapper = mapper;
    }


    @Operation(summary = "Find stores by slug", description = "slug search by %slug% format", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StoreResponse.class)), mediaType = "application/json"))
    })
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<ListResponse<StoreResponse>> findAll(
            @Parameter(description="slug of the store for search.") @RequestParam(required=false) String slug,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int pageNumber
    ) {
        List<StoreResponse> list = storeService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(list));
    }





    @Operation(summary = "Find store by storeId", description = "Returns a single store", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = StoreResponse.class))),
            @ApiResponse(responseCode = "404", description = "store not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping(value = "/{storeId}", produces = "application/json")
    public ResponseEntity<StoreResponse> findById(
            @Parameter(description="Id of the store to be obtained. Cannot be empty.", required=true)
            @PathVariable @Min(0) int storeId) {
        StoreResponse response = mapper.toDto(storeService.findById(storeId));
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Add a new store", description = "", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "store created", content = @Content(schema = @Schema(implementation = StoreResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "store already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<StoreResponse> save(
            @Parameter(description="store to add. Cannot null or empty.", required=true, schema=@Schema(implementation = StoreRequest.class))
            @Valid @RequestBody StoreRequest request
    ) throws URISyntaxException {
        final StoreEntity entityRequest = mapper.toEntity(request);
        final StoreEntity entityResponse = storeService.saveOrUpdate(entityRequest, null);
        final StoreResponse response = mapper.toDto(entityResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Update a store", description = "", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "store created", content = @Content(schema = @Schema(implementation = StoreResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "store already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PatchMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable int id, @RequestBody Map<Object, Object> fields) throws URISyntaxException {
        StoreEntity store = storeService.findById(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(StoreEntity.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, store, value);
        });
        StoreEntity updatedStore = storeService.saveOrUpdate(store, null);

        return new ResponseEntity<>(mapper.toDto(updatedStore), HttpStatus.OK);
    }


    /*
    @Operation(summary = "Add a new store", description = "", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "store created", content = @Content(schema = @Schema(implementation = StoreResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "store already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(value = "")
    public ResponseEntity<StoreResponse> saveWithImage(
            @Parameter(description="store to add. Cannot null or empty.", required=true, schema=@Schema(implementation = StoreRequest.class))
            @RequestPart(required=true) @Valid StoreRequest request,
            @Parameter(description="store image.", required=false, schema=@Schema(implementation = MultipartFile.class))
            @RequestPart(value="file", required = false) final MultipartFile file
    ) throws URISyntaxException {
        final StoreEntity entityRequest = mapper.toEntity(request);
        final StoreEntity entityResponse = storeService.insert(entityRequest, file);
        final StoreResponse response = mapper.toDto(entityResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    */





    @Operation(summary = "Update an existing store", description = "", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = StoreResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping(value = "/{storeId}", consumes = { "application/json" })
    public ResponseEntity<StoreResponse> update(
            @Parameter(description="Id of the store to be update. Cannot be empty.", required=true)
            @PathVariable int storeId,
            @Parameter(description="store to update. Cannot null or empty.", required=true, schema=@Schema(implementation = StoreRequest.class))
            @Valid @RequestBody  StoreRequest request) {
        StoreResponse response = mapper.toDto(storeService.updateStore(storeId, request));
        return ResponseEntity.ok(response);
    }





    @Operation(summary = "Deletes a store", description = "", tags = { "stores" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "store not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description="Id of the store to be delete. Cannot be empty.", required=true)
            @PathVariable int storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
