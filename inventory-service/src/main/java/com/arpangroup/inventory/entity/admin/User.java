package com.arpangroup.inventory.entity.admin;

import com.arpangroup.inventory.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
//@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;

    private String address;
    private BigDecimal phoneNumber;
    private String email;
    private String fName;
    private String lName;
    private String userName;
    private BigDecimal version;

    //bi-directional many-to-one association to UserRole
    @OneToMany(mappedBy="user")
    private List<UserRole> userRoles;

    public UserRole addUserRole(UserRole userRole) {
        getUserRoles().add(userRole);
        userRole.setUser(this);

        return userRole;
    }

    public UserRole removeUserRole(UserRole userRole) {
        getUserRoles().remove(userRole);
        userRole.setUser(null);

        return userRole;
    }


}
