package com.arpangroup.onboarding.entity.global;

import com.arpangroup.onboarding.entity.StoreEntity;
import com.arpangroup.onboarding.entity.global.CityEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "cuisines")
@Data
//@Audited
@NoArgsConstructor
public class CuisineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_id")
    private int id;

    @Column(name = "cuisine_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cuisines")
    private Set<StoreEntity> stores = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cuisines")
    private Set<CityEntity> cities = new HashSet<>();


    public CuisineEntity(String name) {
        this.name = name;
    }


    public void assignCity(CityEntity cityEntity){
        if (this.cities == null){
            this.cities = new HashSet<>();
        }
        this.cities.add(cityEntity);
    }
}
