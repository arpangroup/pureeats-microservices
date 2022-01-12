package com.arpangroup.onboarding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "pan_details")
@Data
@Audited
@NoArgsConstructor
public class PanEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pan_no", nullable = false)
    @Size(min = 10, max = 10, message = "must be 10 characters long")
    private String panNo;

    @Column(name = "pan_name", nullable = false)
    private String panName;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "is_terms_accepted", columnDefinition = "tinyint(1) default 0")
    private boolean isTermsAccepted;

    @Column(name = "is_verified", columnDefinition = "tinyint(1) default 0")
    private boolean isVerified;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date verifiedAt;

    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "remarks", nullable = true)
    @Size(max = 200, message = "must be minimum 5 characters long")
    private String remarks;

}
