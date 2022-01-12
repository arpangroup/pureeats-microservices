package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends RevisionRepository<StockEntity, Integer, Integer>, JpaRepository<StockEntity, Integer> {
}
