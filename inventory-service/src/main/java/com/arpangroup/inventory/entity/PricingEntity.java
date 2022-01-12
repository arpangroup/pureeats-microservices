package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.entity.delete.ProductPricing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
//@NamedQuery(name="Pricing.findAll", query="SELECT p FROM Pricing p")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Audited
public class PricingEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "pricing_id")
    private int pricingId;

    @Column(name = "ref_id")
    @NotNull(message = "ref_id should not be null")
    private Long refId;// restaurantId

    private String pricingName;


    /*##################################################################*/
    private Float purchasePrice;
    private Float sellingPrice;
    private Float defaultPrice;//oldPrice
    private Float displayPrice;//price
    /*##################################################################*/

    private BigDecimal pricingDiscountPercentage;

    @Temporal(TemporalType.DATE)
    private Date effectiveFrom;

    @Temporal(TemporalType.DATE)
    private Date effectiveTo;

    private BigDecimal version;

    //bi-directional many-to-one association to ProductPricing
    @OneToMany(mappedBy="pricingEntity")
    private List<ProductPricing> productPricings;


    public ProductPricing addProductPricing(ProductPricing productPricing) {
        getProductPricings().add(productPricing);
        productPricing.setPricingEntity(this);
        return productPricing;
    }

    public ProductPricing removeProductPricing(ProductPricing productPricing) {
        getProductPricings().remove(productPricing);
        productPricing.setPricingEntity(null);
        return productPricing;
    }

}
