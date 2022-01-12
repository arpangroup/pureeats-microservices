package com.arpangroup.inventory.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
//@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int refId;

    //bi-directional many-to-one association to Role
    @ManyToOne
    private Role role;

    //bi-directional many-to-one association to User
    @ManyToOne
    private User user;
}
