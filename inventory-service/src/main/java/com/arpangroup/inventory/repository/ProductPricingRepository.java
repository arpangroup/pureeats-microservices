package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.delete.ProductPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricingRepository extends RevisionRepository<ProductPricing, Integer, Integer>, JpaRepository<ProductPricing, Integer> {
}
