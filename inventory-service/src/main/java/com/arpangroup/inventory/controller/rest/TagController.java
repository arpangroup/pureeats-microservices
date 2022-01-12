package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.request.TagRequest;
import com.arpangroup.inventory.dto.response.ListResponse;
import com.arpangroup.inventory.dto.response.category.CategoryResponseDto;
import com.arpangroup.inventory.dto.response.tag.TagResponse;
import com.arpangroup.inventory.exception.ApiError;
import com.arpangroup.inventory.mapper.CategoryMapper;
import com.arpangroup.inventory.mapper.TagMapper;
import com.arpangroup.inventory.service.CategoryService;
import com.arpangroup.inventory.service.TagService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static com.arpangroup.inventory.controller.rest.BaseApiController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/tags")
@Tag(name = "tag", description = "the tag API")
public class TagController extends BaseApiController{
    private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);
    private final TagService tagService;
    private final TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }


    @Operation(summary = "Find tags by name", description = "tag search by %name% format", tags = { "tag" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TagResponse.class)), mediaType = "application/json"))
    })
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<ListResponse<TagResponse>> getAllTags(
            @Parameter(description="Name of the tag for search.") @RequestParam(required=false) String name,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int pageNumber
    ) {
        List<TagResponse> list = tagService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(list));
    }





    @Operation(summary = "Find tag by tagName", description = "Returns a single tag", tags = { "tag" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = TagResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "tag not found", content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    @GetMapping(value = "/{tagName}", produces = "application/json")
    public ResponseEntity<TagResponse> getTag(
            @Parameter(description="name of the tag to be obtained. Cannot be empty.", required=true)
            @PathVariable(required = true) String tagName) {
        TagResponse response = mapper.toDto(tagService.findByTagName(tagName));
        return ResponseEntity.ok(response);
    }





    @Operation(summary = "Add a new tag", description = "", tags = { "tag" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tag created", content = @Content(schema = @Schema(implementation = TagResponse.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "V109", description = "tag already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping(value = "")
    public ResponseEntity<TagResponse> addCTag(
            @Parameter(description="tag to add. Cannot null or empty.", required=true, schema=@Schema(implementation = TagRequest.class))
            @Valid @RequestBody TagRequest request) throws URISyntaxException {
        TagResponse response = mapper.toDto(tagService.insert(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Deletes a tag", description = "", tags = { "tag" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "tagName not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{tagName}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description="Id of the tag to be delete. Cannot be empty.", required=true)
            @PathVariable String tagName) {
        tagService.deleteTag(tagName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
