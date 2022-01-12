package com.arpangroup.inventory.entity.admin;

import com.arpangroup.inventory.common.MenuType;
import com.arpangroup.inventory.entity.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin_tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Audited
@Data
public class MenuEntity extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int menuId;

    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    private String menuName;
    private Integer parentId;
    @Column(name = "menu_url")
    private String menuURL;
    private String menuIcon;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 0")
    private boolean isActive;

    public MenuEntity(String menuName) {
        this.menuName = menuName;
        this.menuType = MenuType.MAIN_MENU;
    }
    public MenuEntity(String menuName, MenuType menuType, int parentId) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.parentId = parentId;
    }
}
