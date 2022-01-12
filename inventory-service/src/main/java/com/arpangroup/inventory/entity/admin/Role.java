package com.arpangroup.inventory.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
//@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    private String roleName;

    private BigDecimal version;

    //bi-directional many-to-one association to RolePermission
    @OneToMany(mappedBy="role")
    private List<RolePermission> rolePermissions;

    //bi-directional many-to-one association to UserRole
    @OneToMany(mappedBy="role")
    private List<UserRole> userRoles;
}
