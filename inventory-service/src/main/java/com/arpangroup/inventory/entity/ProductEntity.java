package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.common.ItemState;
import com.arpangroup.inventory.common.ItemType;
import com.arpangroup.inventory.entity.delete.ProductInvoice;
import com.arpangroup.inventory.entity.delete.ProductPricing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
public class ProductEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;

    @Column(name = "name")
    @NotEmpty(message = "product_name is mandatory")
    @Size(min = 2)
    private String productName;

    @Column(name = "store_id")
    private int storeId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity categoryEntity;

    @Column(name = "purchase_price")
    @NotNull(message = "purchase_price should not be null")
    private Float purchasePrice;//min_price

    @Column(name = "selling_price")
    @NotNull(message = "selling_price should not be null")
    private Float sellingPrice;//max_price

    @Column(name = "default_price")
    @NotNull(message = "default_price should not be null")
    private Float defaultPrice;//oldPrice

    @Column(name = "display_price")
    @NotNull(message = "display_price should not be null")
    private Float displayPrice;//price

    private String image;
    private String description;

    @Enumerated(EnumType.STRING)
    private ItemState itemState;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column(name = "is_veg", columnDefinition = "tinyint(1) default 0")
    private boolean isVeg;
    @Column(name = "is_recommended", columnDefinition = "tinyint(1) default 0")
    private boolean isRecommended;
    @Column(name = "is_popular", columnDefinition = "tinyint(1) default 0")
    private boolean isPopular;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_tag_mapping",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<TagEntity> tagEntities = new HashSet<>();

    //bi-directional many-to-one association to ProductInvoice
    @OneToMany(mappedBy="productEntity")
    private List<ProductInvoice> productInvoices;

    //bi-directional many-to-one association to ProductPricing
    @OneToMany(mappedBy="productEntity")
    private List<ProductPricing> productPricings;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="productEntity")
    private List<StockEntity> stockEntities;

    private BigDecimal version;

    public ProductInvoice addProductInvoice(ProductInvoice productInvoice) {
        getProductInvoices().add(productInvoice);
        productInvoice.setProductEntity(this);
        return productInvoice;
    }

    public ProductInvoice removeProductInvoice(ProductInvoice productInvoice) {
        getProductInvoices().remove(productInvoice);
        productInvoice.setProductEntity(null);
        return productInvoice;
    }

    public ProductPricing addProductPricing(ProductPricing productPricing) {
        getProductPricings().add(productPricing);
        productPricing.setProductEntity(this);
        return productPricing;
    }

    public ProductPricing removeProductPricing(ProductPricing productPricing) {
        getProductPricings().remove(productPricing);
        productPricing.setProductEntity(null);
        return productPricing;
    }

    public StockEntity addStock(StockEntity stockEntity) {
        getStockEntities().add(stockEntity);
        stockEntity.setProductEntity(this);
        return stockEntity;
    }

    public StockEntity removeStock(StockEntity stockEntity) {
        getStockEntities().remove(stockEntity);
        stockEntity.setProductEntity(null);
        return stockEntity;
    }

    public void addTag(TagEntity tagEntity) {
        if (getTagEntities() == null){
            this.tagEntities = new HashSet<>();
        }
        this.tagEntities.add(tagEntity);
    }

    public void addTags(List<TagEntity> tagEntity) {
        if (getTagEntities() == null){
            this.tagEntities = new HashSet<>();
        }
        tagEntity.forEach(this::addTag);
    }

    public void removeStock(TagEntity tagEntity) {
        if (getTagEntities() == null) {
            return ;
        }
        this.tagEntities.remove(tagEntity);
    }

}
