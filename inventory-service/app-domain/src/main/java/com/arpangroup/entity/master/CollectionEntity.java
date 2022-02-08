package com.arpangroup.entity.master;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "collections")
//@Audited
@Data
@NoArgsConstructor
//public class CityEntity extends Auditable<String>{
public class CollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer collectionId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "res_count", columnDefinition = "integer default 0")
    private Integer resCount;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "collections")
    @JsonBackReference
    private Set<CityEntity> cities = new HashSet<>();

    public CollectionEntity(@NotNull String title, String description) {
        this.title = title;
        this.description = description;
    }

}
