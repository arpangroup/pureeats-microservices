package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.PricingEntity;
import com.arpangroup.inventory.repository.PricingRepository;
import com.arpangroup.inventory.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    public Iterable<PricingEntity> getAllStock() {
        return pricingRepository.findAll();
    }

    public void insert(PricingEntity pricingEntity) {
        pricingRepository.save(pricingEntity);
    }


    public Iterable<PricingEntity> findAll() {
        return pricingRepository.findAll();
    }

    public Optional<PricingEntity> find(int id) {
        return pricingRepository.findById(id);
    }

    public void updatePricing(PricingEntity pricingEntity) {
        pricingRepository.save(pricingEntity);
    }

    public void deletePricing(PricingEntity pricingEntity) {
        pricingRepository.delete(pricingEntity);
    }
}
