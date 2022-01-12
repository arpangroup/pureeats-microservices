package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.delete.SupplierEntity;
import com.arpangroup.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public void insert(SupplierEntity supplierEntity) {
        supplierRepository.save(supplierEntity);
    }


    public Optional<SupplierEntity> findById(int id) {
        return supplierRepository.findById(id);
    }

    public Iterable<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    public void updateSupplier(SupplierEntity supplierEntity) {

        supplierRepository.save(supplierEntity);
    }

    public void deleteSupplier(SupplierEntity supplierEntity) {

        supplierRepository.delete(supplierEntity);
    }
}
