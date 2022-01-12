package com.arpangroup.onboarding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "establishment_page")
@Data
@Audited
@NoArgsConstructor
public class EstablishmentWebPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String description;

    private String disclaimer;
}
