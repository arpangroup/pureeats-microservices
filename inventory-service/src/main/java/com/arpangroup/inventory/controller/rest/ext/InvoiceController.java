package com.arpangroup.inventory.controller.rest.ext;

import com.arpangroup.inventory.entity.InvoiceEntity;
import com.arpangroup.inventory.service.InvoiceService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Hidden
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping("")
    public Iterable<InvoiceEntity> getAllInvoice() {
        return invoiceService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<InvoiceEntity> searchInvoice(@PathVariable int id) {
        return invoiceService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addInvoice(@RequestBody InvoiceEntity invoiceEntity) {
        invoiceService.insert(invoiceEntity);
    }

    @RequestMapping(method = RequestMethod.PUT,value ="/{id}")
    public void updateInvoice(@RequestBody InvoiceEntity invoiceEntity) {
        invoiceService.updateInvoice(invoiceEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE,value ="/{id}")
    public void deleteInvoice(@RequestBody InvoiceEntity invoiceEntity) {
        invoiceService.deleteInvoice(invoiceEntity);
    }
}
