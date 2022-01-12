package com.arpangroup.onboarding.controller;

import com.arpangroup.onboarding.entity.StoreEntity;
import com.arpangroup.onboarding.schema.OnboardRequest;
import com.arpangroup.onboarding.schema.response.ListResponse;
import com.arpangroup.onboarding.service.OnboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

import static com.arpangroup.onboarding.controller.BaseApiController.BASE_URI;


@RestController
@RequestMapping(BASE_URI + "/onboard")
@Tag(name = "Onboard", description = "")
@Slf4j
public class OnboardController extends BaseApiController{
    private final OnboardService onboardService;

    public OnboardController(OnboardService onboardService) {
        this.onboardService = onboardService;
    }

    @GetMapping("")
    public ResponseEntity<ListResponse<StoreEntity>> findAll(String q, int page) {
        return ResponseEntity.ok(new ListResponse<>(Arrays.asList(new StoreEntity())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreEntity> findById(int id) {
        return null;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OnboardRequest> save(@Valid @RequestBody OnboardRequest OnboardRequest) {
        return ResponseEntity.ok(OnboardRequest);
    }


    public ResponseEntity<StoreEntity> update(@Valid StoreEntity storeEntity) {
        return null;
    }


    public ResponseEntity<StoreEntity> patch(int id, Map<Object, Object> fields) {
        return null;
    }


    public ResponseEntity<Void> deleteById(int id) {
        return null;
    }
}
