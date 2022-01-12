package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.dto.response.ListResponse;
import com.arpangroup.inventory.dto.response.product.ProductResponseDto;
import com.arpangroup.inventory.entity.ProductEntity;
import com.arpangroup.inventory.mapper.ProductMapper;
import com.arpangroup.inventory.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.arpangroup.inventory.controller.rest.BaseApiController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/products")
public class ProductController extends BaseApiController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @Operation(summary = "Find all categories", description = "Find all product products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class, name = "ProductResponse"))
            })
    })
    @GetMapping("")
    public ResponseEntity<ListResponse<ProductResponseDto>> findAll() {
        List<ProductResponseDto> list = productService.findAll().stream().map(mapper::toDtoDetails).collect(Collectors.toList());
        return ResponseEntity.ok(new ListResponse<>(list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable int id) {
        ProductEntity response = productService.findById(id);
        return ResponseEntity.ok(mapper.toDtoDetails(response));
    }

    @PostMapping("")
    public  ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductRequestDto request) {
        ProductEntity response = productService.insert(request);
        return new ResponseEntity<>(mapper.toDtoDetails(response), HttpStatus.CREATED);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable int id, @RequestBody @Valid ProductRequestDto request) {
        ProductEntity response = productService.updateProduct(id, request);
        return ResponseEntity.ok(mapper.toDtoDetails(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
