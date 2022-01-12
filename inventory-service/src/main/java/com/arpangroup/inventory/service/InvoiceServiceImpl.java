package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.InvoiceEntity;
import com.arpangroup.inventory.repository.InvoiceRepository;
import com.arpangroup.inventory.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;


    public void insert(InvoiceEntity invoiceEntity) {
        invoiceRepository.save(invoiceEntity);
    }


    public Optional<InvoiceEntity> findById(int id) {
        return invoiceRepository.findById(id);
    }

    public Iterable<InvoiceEntity> findAll() {
        return invoiceRepository.findAll();
    }

    public void updateInvoice(InvoiceEntity invoiceEntity) {
        invoiceRepository.save(invoiceEntity);
    }

    public void deleteInvoice(InvoiceEntity invoiceEntity) {
        invoiceRepository.delete(invoiceEntity);
    }
}
