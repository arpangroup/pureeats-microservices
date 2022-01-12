package com.arpangroup.inventory.service.impl;

import com.arpangroup.inventory.common.ItemState;
import com.arpangroup.inventory.common.ItemType;
import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.ProductEntity;
import com.arpangroup.inventory.entity.TagEntity;
import com.arpangroup.inventory.exception.IdNotFoundException;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.mapper.ProductMapper;
import com.arpangroup.inventory.repository.CategoryRepository;
import com.arpangroup.inventory.repository.ProductRepository;
import com.arpangroup.inventory.repository.TagRepository;
import com.arpangroup.inventory.service.ProductService;
import com.arpangroup.inventory.service.TagService;
import com.arpangroup.inventory.validator.ProductValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;
    private final ProductValidator validator;
    private final TagRepository tagRepository;

    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository, ProductMapper mapper, ProductValidator validator, TagRepository tagRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.tagRepository = tagRepository;
    }

    @Override
    public ProductEntity insert(ProductRequestDto request) {
        validator.validate(request);
        CategoryEntity category = categoryRepository.findById(request.getProductCategoryId()).orElseThrow(() -> new IdNotFoundException("invalid product_category_id " +request.getProductCategoryId()));

        try{
            ProductEntity entity = mapper.mapToEntity(request);
            if (entity.getDefaultPrice() == null) {
                entity.setDefaultPrice(entity.getSellingPrice());
            }
            if (entity.getDisplayPrice() == null){
                entity.setDisplayPrice(entity.getSellingPrice());
            }
            if(entity.getItemState() == null){
                entity.setItemState(ItemState.AVAILABLE);
            }
            if(entity.getItemType() == null){
                entity.setItemType(ItemType.DISH);
            }

            // Tag need to be present inside tag repository
            if (request.getTags() != null && request.getTags().size() > 0){
                List<TagEntity> tags = tagRepository.findByTagNameIn(request.getTags());
                entity.addTags(tags);
            }

            entity.setCategoryEntity(category);
            return repository.save(entity);
        }catch (IllegalArgumentException e1){
            e1.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public ProductEntity findById(int id) {
        return repository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    @Override
    public ProductEntity findByName(String productName) {
        return null;
    }

    @Override
    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductEntity updateProduct(int id, ProductRequestDto request) {
        ProductEntity entity = repository.findById(id).orElseThrow(() -> new ValidationException(ValidationException.ValidationError.INVALID_ID, "invalid id"));
        if (request.getProductName() != null)entity.setProductName(request.getProductName());
        if (request.getDescription() != null)entity.setDescription(request.getDescription());


        //if (request.getImage() != null)entity.setImage(request.getImage());
        //if (request.getIsActive() != null)entity.setActive(request.getIsActive());
        try{
            return repository.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new ValidationException(ValidationException.ValidationError.DATA_INSERT_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            repository.deleteById(id);
        }catch (IllegalArgumentException e1){
            throw new ValidationException(ValidationException.ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            throw new ValidationException(ValidationException.ValidationError.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }



}
