package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.entity.delete.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
//@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class StockEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int refId;

    private String branchId;

    @Temporal(TemporalType.DATE)
    private Date dateStock;

    private int quantity;
    //private int stockId;

    private BigDecimal version;

    //bi-directional many-to-one association to Product
    @ManyToOne
    @JoinColumn(name="productId")
    private ProductEntity productEntity;

    //bi-directional many-to-one association to Supplier
    @ManyToOne
    @JoinColumn(name="supplierId")
    private SupplierEntity supplierEntity;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name="categoryId")
    private CategoryEntity categoryEntity;
}
