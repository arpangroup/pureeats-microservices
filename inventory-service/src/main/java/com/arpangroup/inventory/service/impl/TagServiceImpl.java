package com.arpangroup.inventory.service.impl;

import com.arpangroup.inventory.controller.rest.CategoryController;
import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.request.TagRequest;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.TagEntity;
import com.arpangroup.inventory.exception.IdNotFoundException;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.exception.ValidationException.ValidationError;
import com.arpangroup.inventory.mapper.CategoryMapper;
import com.arpangroup.inventory.mapper.TagMapper;
import com.arpangroup.inventory.repository.CategoryRepository;
import com.arpangroup.inventory.repository.TagRepository;
import com.arpangroup.inventory.service.CategoryService;
import com.arpangroup.inventory.service.TagService;
import com.arpangroup.inventory.validator.CategoryValidator;
import com.arpangroup.inventory.validator.TagValidator;
import org.hibernate.envers.AuditReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);
    private final TagRepository repository;
    private final TagMapper mapper;
    private final TagValidator validator;
    private final AuditReader auditReader;

    public TagServiceImpl(TagRepository repository, TagMapper mapper, TagValidator validator, AuditReader auditReader) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
        this.auditReader = auditReader;
    }


    @Override
    public TagEntity insert(TagRequest request) throws ValidationException{
        validator.validate(request);
        try{
            TagEntity entity = mapper.toEntity(request);
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
    public TagEntity findByTagName(String tagName) throws IdNotFoundException {
        return repository.findByTagNameIgnoreCase(tagName).orElseThrow(IdNotFoundException::new);
    }

    @Override
    public List<TagEntity> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public void deleteTag(String tagName) {
        try {
            TagEntity tagEntity = repository.findByTagNameIgnoreCase(tagName).orElseThrow(IdNotFoundException::new);
            repository.deleteById(tagEntity.getId());
        }catch (IllegalArgumentException e1){
            throw new ValidationException(ValidationError.ILLEGAL_ARGUMENT, e1.getMessage());
        }catch (Exception e){
            throw new ValidationException(ValidationError.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
