package com.arpangroup.onboarding.repository;

import com.arpangroup.onboarding.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
//    public Page<StoreEntity> findStoreById(@Param("id") int id, Pageable pageable);
}
