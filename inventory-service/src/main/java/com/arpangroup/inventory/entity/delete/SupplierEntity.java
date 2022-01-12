package com.arpangroup.inventory.entity.delete;

import com.arpangroup.inventory.entity.Auditable;
import com.arpangroup.inventory.entity.StockEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
//@NamedQuery(name="Supplier.findAll", query="SELECT s FROM Supplier s")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class SupplierEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int supplierId;

    private String supplierCompany;
    private BigDecimal supplierContact;
    private String supplierName;
    private BigDecimal version;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="supplierEntity")
    private List<StockEntity> stockEntities;

    public StockEntity addStock(StockEntity stockEntity) {
        getStockEntities().add(stockEntity);
        stockEntity.setSupplierEntity(this);
        return stockEntity;
    }

    public StockEntity removeStock(StockEntity stockEntity) {
        getStockEntities().remove(stockEntity);
        stockEntity.setSupplierEntity(null);
        return stockEntity;
    }

}
