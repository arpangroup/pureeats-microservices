package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends RevisionRepository<InvoiceEntity, Integer, Integer>, JpaRepository<InvoiceEntity, Integer> {
}
