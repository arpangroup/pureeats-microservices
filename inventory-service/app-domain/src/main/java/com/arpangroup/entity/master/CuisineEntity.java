package com.arpangroup.entity.master;

import com.arpangroup.entity.StoreEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "cuisines")
@Getter
@Setter
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
    @JsonManagedReference
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
