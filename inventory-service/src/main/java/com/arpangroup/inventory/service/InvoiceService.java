package com.arpangroup.inventory.service;


import com.arpangroup.inventory.entity.InvoiceEntity;

import java.util.Optional;


public interface InvoiceService {
    public void insert(InvoiceEntity invoiceEntity);
    public Optional<InvoiceEntity> findById(int id);
    public Iterable<InvoiceEntity> findAll();
    public void updateInvoice(InvoiceEntity invoiceEntity);
    public void deleteInvoice(InvoiceEntity invoiceEntity);
}
