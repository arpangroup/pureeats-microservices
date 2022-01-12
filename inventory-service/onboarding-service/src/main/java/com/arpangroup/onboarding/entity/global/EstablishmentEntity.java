package com.arpangroup.onboarding.entity.global;

import com.arpangroup.onboarding.common.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
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
    private Set<CityEntity> cities = new HashSet<>();

    public EstablishmentEntity(String name) {
        this.name = name;
    }
}
