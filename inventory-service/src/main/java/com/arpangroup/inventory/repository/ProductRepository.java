package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends RevisionRepository<ProductEntity, Integer, Integer>, JpaRepository<ProductEntity, Integer> {
    public Optional<ProductEntity> findByStoreIdAndProductNameIgnoreCase(int storeId, String productName);
}
