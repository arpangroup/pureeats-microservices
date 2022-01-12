package com.arpangroup.inventory.entity;

import com.arpangroup.inventory.common.AddonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "addon_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddonCategory extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AddonType addonType;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    @ManyToMany(mappedBy = "addonCategories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AddonEntity> addonEntities;


}
