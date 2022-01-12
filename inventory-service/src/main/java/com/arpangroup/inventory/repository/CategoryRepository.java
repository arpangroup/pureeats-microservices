package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends RevisionRepository<CategoryEntity, Integer, Integer>, JpaRepository<CategoryEntity, Integer> {
    public Optional<CategoryEntity> findByCategoryNameIgnoreCase(String categoryName);
}
