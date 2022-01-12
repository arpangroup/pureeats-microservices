package com.arpangroup.inventory.entity.delete;

import com.arpangroup.inventory.entity.InvoiceEntity;
import com.arpangroup.inventory.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_invoice")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class ProductInvoice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int refId;

    //bi-directional many-to-one association to Invoice
    @ManyToOne
    private InvoiceEntity invoiceEntity;

    //bi-directional many-to-one association to Product
    @ManyToOne
    private ProductEntity productEntity;
}
