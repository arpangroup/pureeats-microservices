package com.arpangroup.inventory.service.impl;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.exception.IdNotFoundException;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.exception.ValidationException.ValidationError;
import com.arpangroup.inventory.mapper.CategoryMapper;
import com.arpangroup.inventory.repository.CategoryRepository;
import com.arpangroup.inventory.service.CategoryService;
import com.arpangroup.inventory.validator.CategoryValidator;
import org.hibernate.envers.AuditReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final CategoryValidator validator;
    private final AuditReader auditReader;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryValidator validator, AuditReader auditReader) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.auditReader = auditReader;
    }


    @Override
    public CategoryEntity insert(CategoryRequestDto request) throws ValidationException{
        validator.validate(request);
        try{
            CategoryEntity entity = mapper.toEntity(request);
            return repository.save(entity);
        }catch (IllegalArgumentException e1){
            e1.printStackTrace();
            throw new ValidationException(ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public CategoryEntity findById(int categoryId) throws IdNotFoundException {
        return repository.findById(categoryId).orElseThrow(IdNotFoundException::new);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public CategoryEntity updateCategory(int categoryId, CategoryRequestDto request) {
        CategoryEntity entity = repository.findById(categoryId).orElseThrow(() -> new ValidationException(ValidationError.INVALID_ID, "invalid id"));
        if (request.getCategoryName() != null)entity.setCategoryName(request.getCategoryName());
        if (request.getDescription() != null)entity.setDescription(request.getDescription());
        //if (request.getImage() != null)categoryEntity.setImage(request.getImage());
        if (request.getIsActive() != null)entity.setActive(request.getIsActive());
        try{
            return repository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        try {
            repository.deleteById(categoryId);
        }catch (IllegalArgumentException e1){
            throw new ValidationException(ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            throw new ValidationException(ValidationError.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
