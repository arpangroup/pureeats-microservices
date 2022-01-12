package com.arpangroup.inventory.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="permission_log")
//@NamedQuery(name="PermissionLog.findAll", query="SELECT p FROM PermissionLogEntity p")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionLogEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int permissionId;

    private String permissionName;

}
