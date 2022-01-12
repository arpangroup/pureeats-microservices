package com.arpangroup.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tags")
@Data
@Audited
@NoArgsConstructor
public class TagEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tag_name")
    private String tagName;

    @Size(max = 100, message = "should be maximum 100 characters long")
    private String description;

    @ManyToMany(mappedBy = "tagEntities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductEntity> productEntities = new HashSet<>();

    public TagEntity(String tagName) {
        this.tagName = tagName;
    }
}
