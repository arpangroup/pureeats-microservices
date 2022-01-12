package com.arpangroup.onboarding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "fssai_details")
@Data
@Audited
@NoArgsConstructor
public class FssaiEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "certificate_no", nullable = false)
    @Size(min = 5, max = 20, message = "must be minimum 5 characters long")
    private String certificateNo;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date expiryDate;

    @Column(name = "have_applied_for_certificate")
    private Boolean haveAppliedForCertificate;

    @Column(name = "image", nullable = true)
    private String image;

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
