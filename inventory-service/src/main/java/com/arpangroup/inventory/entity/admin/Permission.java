package com.arpangroup.inventory.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
//@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int permissionId;

    private String permissionName;

    //bi-directional many-to-one association to RolePermission
    @OneToMany(mappedBy="permission")
    private List<RolePermission> rolePermissions;


    public RolePermission addRolePermission(RolePermission rolePermission) {
        getRolePermissions().add(rolePermission);
        rolePermission.setPermission(this);
        return rolePermission;
    }

    public RolePermission removeRolePermission(RolePermission rolePermission) {
        getRolePermissions().remove(rolePermission);
        rolePermission.setPermission(null);
        return rolePermission;
    }
}
