package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.entity.PricingEntity;
import com.arpangroup.inventory.service.PricingService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Hidden
@RestController
@RequestMapping("/pricings")
public class PricingController {
    @Autowired
    public PricingService pricingService;
//    @Autowired private PricingLogService pricingLogService;

    @RequestMapping("")
    public Iterable<PricingEntity> getAllPricing() {
        return pricingService.findAll();
    }


    @RequestMapping("/{id}")
    public Optional<PricingEntity> searchPricing(@PathVariable int id) {
        return pricingService.find(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addPricing(@RequestBody PricingEntity pricingEntity) {
        pricingService.insert(pricingEntity);
        //pricingLogService.insert(TheLogConverter.pricingLogLogConverter(pricing));
    }

    @RequestMapping(method = RequestMethod.PUT,value ="/{id}")
    public void updateCategory(@RequestBody PricingEntity pricingEntity) {
        pricingService.updatePricing(pricingEntity);
        //pricingLogService.insert(TheLogConverter.pricingLogLogConverter(pricing));
    }

    @RequestMapping(method = RequestMethod.DELETE,value ="/{id}")
    public void deletePricing(@RequestBody PricingEntity pricingEntity) {
        pricingService.deletePricing(pricingEntity);
        //pricingLogService.insert(TheLogConverter.pricingLogLogConverter(pricing));
    }

}
