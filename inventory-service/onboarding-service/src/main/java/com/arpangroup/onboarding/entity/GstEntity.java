package com.arpangroup.onboarding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "gst_details")
@Data
@Audited
@NoArgsConstructor
public class GstEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "gst_no", nullable = false)
    @Size(min = 5, max = 20, message = "must be 5 characters long")
    private String gstNo;

    @Column(name = "is_gst_registered", columnDefinition = "tinyint(1) default 0")
    private boolean isGstRegistered;

    @Column(name = "is_same_gst_for_all_items", columnDefinition = "tinyint(1) default 0")
    private boolean isSameGstForAllItems;

    @Column(name = "is_terms_accepted", columnDefinition = "tinyint(1) default 0")
    private boolean isTermsAccepted;

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
