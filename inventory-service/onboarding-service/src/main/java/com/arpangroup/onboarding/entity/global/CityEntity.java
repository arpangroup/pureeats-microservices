package com.arpangroup.onboarding.entity.global;

import com.arpangroup.onboarding.schema.response.Establishment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity(name = "city")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueCityNameAndLatLng", columnNames = {"city_name", "lat", "lng"})
        //@UniqueConstraint(name = "UniqueOwnerAndContactNumber", columnNames = {"owner", "contact_number"})
})
@Data
//@Audited
@NoArgsConstructor
//public class CityEntity extends Auditable<String>{
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city_name", nullable = false)
    private String name;

    @Column(name = "country_id", columnDefinition = "integer default 1")
    private Integer countryId = 1;

    @Column(name = "country_name", columnDefinition = "varchar(255) default 'India'")
    private String countryName;

    private String countryFlagUrl;

    @Column(name = "lat", nullable = false)
    private Float lat;

    @Column(name = "lng", nullable = false)
    private Float lng;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "city_cuisines",
            joinColumns = {@JoinColumn(name = "city_id")},
            inverseJoinColumns = {@JoinColumn(name = "cuisine_id")}
    )
    private Set<CuisineEntity> cuisines = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "city_collections",
            joinColumns = {@JoinColumn(name = "city_id")},
            inverseJoinColumns = {@JoinColumn(name = "collection_id")}
    )
    private Set<CollectionEntity> collections = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "city_establishments",
            joinColumns = {@JoinColumn(name = "city_id")},
            inverseJoinColumns = {@JoinColumn(name = "establishment_id")}
    )
    private Set<EstablishmentEntity> establishments = new HashSet<>();



    public CityEntity(String name, Float lat, Float lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }


    public CityEntity assignCuisine(CuisineEntity... cuisines){
        if (this.cuisines == null){
            this.cuisines = new HashSet<>();
        }
        this.cuisines.addAll(Arrays.asList(cuisines));
        return this;
    }

    public CityEntity addCollections(CollectionEntity... collections){
        if (this.collections == null){
            this.collections = new HashSet<>();
        }
        this.collections.addAll(Arrays.asList(collections));
        return this;
    }


    public CityEntity addEstablishments(EstablishmentEntity... establishments){
        if (this.establishments == null){
            this.establishments = new HashSet<>();
        }
        this.establishments.addAll(Arrays.asList(establishments));
        return this;
    }
}
