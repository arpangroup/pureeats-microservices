package com.arpangroup.entity.master;

import com.arpangroup.entity.master.CityEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "establishments")
@Data
//@Audited
@NoArgsConstructor
//public class Establishment extends Auditable<String>{
public class EstablishmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "establishments")
    @JsonBackReference
    private Set<CityEntity> cities = new HashSet<>();

    public EstablishmentEntity(String name) {
        this.name = name;
    }
}
