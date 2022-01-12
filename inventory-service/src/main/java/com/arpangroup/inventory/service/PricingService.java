package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.PricingEntity;

import java.util.Optional;

public interface PricingService {
    public Iterable<PricingEntity> getAllStock();
    public void insert(PricingEntity pricingEntity);
    public Iterable<PricingEntity> findAll();
    public Optional<PricingEntity> find(int id);
    public void updatePricing(PricingEntity pricingEntity);
    public void deletePricing(PricingEntity pricingEntity);
}
