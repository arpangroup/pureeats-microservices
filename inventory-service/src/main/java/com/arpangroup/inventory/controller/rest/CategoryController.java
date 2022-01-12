package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.response.category.CategoryResponseDto;
import com.arpangroup.inventory.dto.response.ListResponse;
import com.arpangroup.inventory.dto.response.product.ProductResponseDto;
import com.arpangroup.inventory.exception.ApiError;
import com.arpangroup.inventory.mapper.CategoryMapper;
import com.arpangroup.inventory.service.CategoryService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static com.arpangroup.inventory.controller.rest.BaseApiController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/categories")
@Tag(name = "category", description = "the category API")
public class CategoryController extends BaseApiController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }


    @Operation(summary = "Find categories by name", description = "Name search by %name% format", tags = { "category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDto.class)), mediaType = "application/json"))
    })
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<ListResponse<CategoryResponseDto>> getAllCategory(
            @Parameter(description="Name of the category for search.") @RequestParam(required=false) String name,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int pageNumber
    ) {
        List<CategoryResponseDto> list = categoryService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(list));
    }





    @Operation(summary = "Find category by categoryId", description = "Returns a single category", tags = { "category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "category not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<CategoryResponseDto> getCategory(
            @Parameter(description="Id of the category to be obtained. Cannot be empty.", required=true)
            @PathVariable int categoryId) {
        CategoryResponseDto response = mapper.toDto(categoryService.findById(categoryId));
        return ResponseEntity.ok(response);
    }





    @Operation(summary = "Add a new category", description = "", tags = { "category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "category created", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "category already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(value = "", consumes = { "application/json" })
    public ResponseEntity<CategoryResponseDto> addCategory(
            @Parameter(description="category to add. Cannot null or empty.", required=true, schema=@Schema(implementation = CategoryRequestDto.class))
            @Valid @RequestBody CategoryRequestDto request) throws URISyntaxException {
        CategoryResponseDto response = mapper.toDto(categoryService.insert(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





    @Operation(summary = "Update an existing category", description = "", tags = { "category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Contact not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping(value = "/{categoryId}", consumes = { "application/json" })
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @Parameter(description="Id of the category to be update. Cannot be empty.", required=true)
            @PathVariable int categoryId,
            @Parameter(description="category to update. Cannot null or empty.", required=true, schema=@Schema(implementation = CategoryRequestDto.class))
            @Valid @RequestBody  CategoryRequestDto request) {
        CategoryResponseDto response = mapper.toDto(categoryService.updateCategory(categoryId, request));
        return ResponseEntity.ok(response);
    }





    @Operation(summary = "Deletes a category", description = "", tags = { "category" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "category not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description="Id of the category to be delete. Cannot be empty.", required=true)
            @PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
