package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.admin.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMenuRepository extends RevisionRepository<MenuEntity, Integer, Integer>, JpaRepository<MenuEntity, Integer> {
//    List<RoleMenuVo> getMenuByRoleForAll();
//    Iterable<MenuEntity> findByRoleName(List<String> roleName);
}
