package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.delete.ProductInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInvoiceRepository extends RevisionRepository<ProductInvoice, Integer, Integer>, JpaRepository<ProductInvoice,Integer> {
}
