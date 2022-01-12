package com.arpangroup.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
public class CategoryEntity extends Auditable<String>  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(name = "name", unique = true)
    private String categoryName;

    private String description;
    private String image;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    private BigDecimal version;

    //bi-directional many-to-one association to Product
    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntities;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="categoryEntity")
    private List<StockEntity> stockEntities;

    public void addProduct(ProductEntity productEntity) {
        if (getProductEntities() == null){
            this.productEntities = new ArrayList<>();
        }
        getProductEntities().add(productEntity);
        productEntity.setCategoryEntity(this);
    }

    public void removeProduct(ProductEntity productEntity) {
        if (getProductEntities() == null) return ;
        getProductEntities().remove(productEntity);
        productEntity.setCategoryEntity(null);
    }

    public StockEntity addStock(StockEntity stockEntity) {
        getStockEntities().add(stockEntity);
        stockEntity.setCategoryEntity(this);

        return stockEntity;
    }

    public StockEntity removeStock(StockEntity stockEntity) {
        getStockEntities().remove(stockEntity);
        stockEntity.setCategoryEntity(null);

        return stockEntity;
    }
}
