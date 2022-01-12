package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends RevisionRepository<PricingEntity, Integer, Integer>, JpaRepository<PricingEntity, Integer> {
}
