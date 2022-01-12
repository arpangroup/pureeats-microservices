package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.delete.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends RevisionRepository<SupplierEntity, Integer, Integer>, JpaRepository<SupplierEntity, Integer> {
}
