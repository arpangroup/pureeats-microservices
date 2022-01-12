package com.arpangroup.onboarding.entity;

import com.arpangroup.onboarding.common.EntityType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "contact_details")
@Data
@Audited
@NoArgsConstructor
public class ContactDetailsEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "entity_type", columnDefinition = "ENUM('ESTABLISHMENT', 'ESTABLISHMENT_OWNER', 'USER')", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "phone", nullable = true)
    private Long contactNumber;

    @Column(name = "email", nullable = true)
    private Long email;

    @Column(name = "is_phone_verified", columnDefinition = "tinyint(1) default 0")
    private boolean isPhoneVerified;

    @Column(name = "is_email_verified", columnDefinition = "tinyint(1) default 0")
    private boolean isEmailVerified;

    @Column(name = "is_notifiable", columnDefinition = "tinyint(1) default 0")
    private boolean isNotifiable;

}
