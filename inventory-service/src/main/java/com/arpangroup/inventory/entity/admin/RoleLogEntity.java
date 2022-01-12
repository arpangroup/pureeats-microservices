package com.arpangroup.inventory.entity.admin;

import com.arpangroup.inventory.entity.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="role_log")
//@NamedQuery(name="RoleLog.findAll", query="SELECT r FROM RoleLogEntity r")
public class RoleLogEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId;

    private String roleName;
    private BigDecimal version;
}
