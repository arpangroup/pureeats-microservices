package com.arpangroup.inventory.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="role_permission")
//@NamedQuery(name="RolePermission.findAll", query="SELECT r FROM RolePermission r")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int refId;

    //bi-directional many-to-one association to Permission
    @ManyToOne
    private Permission permission;

    //bi-directional many-to-one association to Role
    @ManyToOne
    private Role role;

}
