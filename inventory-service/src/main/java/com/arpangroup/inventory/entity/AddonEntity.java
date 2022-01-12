package com.arpangroup.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "addons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddonEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "name is mandatory")
    @Size(min = 2)
    private String name;

    @NotNull(message = "price should not be null")
    private BigInteger price;

    @ManyToOne
    @JoinColumn(name = "addon_category_id")
    private AddonCategory addonCategory;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "addon_category_item_mapping",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "addon_category_id") })
    private Set<AddonCategory> addonCategories = new HashSet<>();

}
