package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends RevisionRepository<StoreEntity, Integer, Integer>, JpaRepository<StoreEntity, Integer> {
    public Optional<StoreEntity> findByStoreNameIgnoreCase(String storeName);
    public Optional<StoreEntity> findBySlugIgnoreCase(String slug);
    public Optional<StoreEntity> findByStoreOwnerIdAndStoreNameIgnoreCase(int storeOwnerId, String storeName);
}
