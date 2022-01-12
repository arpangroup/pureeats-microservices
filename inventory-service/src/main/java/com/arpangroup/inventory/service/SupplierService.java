package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.delete.SupplierEntity;

import java.util.Optional;

public interface SupplierService {
    public void insert(SupplierEntity supplierEntity);
    public Optional<SupplierEntity> findById(int id);
    public Iterable<SupplierEntity> findAll();
    public void updateSupplier(SupplierEntity supplierEntity);
    public void deleteSupplier(SupplierEntity supplierEntity);
}
