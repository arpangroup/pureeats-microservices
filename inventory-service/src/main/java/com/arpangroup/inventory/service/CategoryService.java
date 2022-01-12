package com.arpangroup.inventory.service;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.response.category.CategoryResponseDto;
import com.arpangroup.inventory.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    public CategoryEntity insert(CategoryRequestDto request);
    public CategoryEntity findById(int id);
    public List<CategoryEntity> findAll();
    public CategoryEntity updateCategory(int id, CategoryRequestDto request);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
    public void deleteCategory(int id);
}
