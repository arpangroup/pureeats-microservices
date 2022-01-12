package com.arpangroup.inventory.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;

@Entity
@Table(name = "revision_info")
@RevisionEntity(AuditRevisionListener.class)
@NoArgsConstructor
@Data
@AttributeOverrides({
        @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
        @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
})
public class AuditRevisionEntity extends DefaultRevisionEntity {
    @Column(name = "user")
    private String user;
}
