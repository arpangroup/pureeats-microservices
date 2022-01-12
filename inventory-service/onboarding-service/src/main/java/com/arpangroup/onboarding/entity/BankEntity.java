package com.arpangroup.onboarding.entity;

import com.arpangroup.onboarding.common.AccountType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "bank_details")
@Data
@Audited
@NoArgsConstructor
public class BankEntity extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_type", columnDefinition = "ENUM('SAVING', 'CURRENT')", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "acc_no", unique = false, nullable = false)
    @Size(min = 10, max = 30, message = "must be minimum 10 characters long")
    private String accountNo;

    @Column(name = "ifsc", nullable = false)
    @Size(min = 5, max = 20, message = "must be minimum 5 characters long")
    private String ifscCode;

    @Column(name = "bene_name", nullable = false)
    @Size(min = 5, max = 50, message = "must be minimum 5 characters long")
    private String beneName;

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
