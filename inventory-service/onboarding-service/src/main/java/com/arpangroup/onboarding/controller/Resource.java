package com.arpangroup.onboarding.controller;

import com.arpangroup.onboarding.schema.response.ListResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Tag(name = "onboard", description = "the store API")
public interface Resource<T> {
    public String BASE_URI = "/api/v1";

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    ResponseEntity<ListResponse<T>> findAll(
            @Parameter(description="query for search.") @RequestParam(required=false) String q,
            @Parameter(description="Page number, default is 1") @RequestParam(value="page", defaultValue="1") int page
    );

//    @GetMapping("/all")
//    ResponseEntity<ListResponse<T>> findAll();

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable int id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> save(@RequestBody @Valid T t);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> update(@RequestBody @Valid T t);

    @PatchMapping("{id}")
    ResponseEntity<T> patch(@PathVariable int id, @RequestBody Map<Object, Object> fields);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteById(@PathVariable int id);

//    @GetMapping("/invalid")
//    ResponseEntity<String> invalid();
}
