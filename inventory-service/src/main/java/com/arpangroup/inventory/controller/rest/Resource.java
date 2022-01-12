package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.dto.response.ListResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface Resource<T> {
    @GetMapping
    ResponseEntity<ListResponse<T>> findAll();
    //ResponseEntity<Collection<T>> findAll();

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable int id);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> save(@RequestBody @Valid T t);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<T> update(@RequestBody @Valid T t);

    @PatchMapping("{id}")
    ResponseEntity<T> patch(@PathVariable int id, @RequestBody Map<Object, Object> fields);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteById(@PathVariable int id);

    @GetMapping("/invalid")
    ResponseEntity<String> invalid();
}
