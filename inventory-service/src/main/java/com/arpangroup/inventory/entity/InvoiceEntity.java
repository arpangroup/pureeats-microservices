package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.entity.delete.ProductInvoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
//@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class InvoiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int invoiceId;

    private double lineTotal;
    private int productId;
    private String productName;
    private double quantity;
    private double total;
    private BigDecimal version;

    //bi-directional many-to-one association to ProductInvoice
    @OneToMany(mappedBy="invoiceEntity")
    private List<ProductInvoice> productInvoices;

    public ProductInvoice addProductInvoice(ProductInvoice productInvoice) {
        getProductInvoices().add(productInvoice);
        productInvoice.setInvoiceEntity(this);
        return productInvoice;
    }

    public ProductInvoice removeProductInvoice(ProductInvoice productInvoice) {
        getProductInvoices().remove(productInvoice);
        productInvoice.setInvoiceEntity(null);
        return productInvoice;
    }

}
