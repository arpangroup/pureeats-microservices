package com.arpangroup.inventory.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseApiController {
    protected final static String BASE_URI = "/api/v1";
}
