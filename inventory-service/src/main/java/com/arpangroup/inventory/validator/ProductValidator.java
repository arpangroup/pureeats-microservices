package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.entity.TagEntity;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.repository.ProductRepository;
import com.arpangroup.inventory.repository.TagRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    private final ProductRepository repository;
    private final TagRepository tagRepository;


    public ProductValidator(ProductRepository repository, TagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }


    private void validateProductName(String productName) throws ValidationException {
        if (productName == null || productName.isEmpty()) {
            throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "Invalid product_name");
        }
    }


    private void validateDuplicateProductFromDB(int storeId, String productName) throws ValidationException {
        boolean isProductPresent = repository.findByStoreIdAndProductNameIgnoreCase(storeId, productName).isPresent();
        if (isProductPresent) {
            throw new ValidationException(ValidationException.ValidationError.DUPLICATE_PRODUCT, productName +" already exist for the requested store");
        }
    }

    private void validateTagsFromDB(List<String> tagNames) throws ValidationException{
        if (tagNames != null && tagNames.size() > 0){
            List<TagEntity> tagEntities = tagRepository.findByTagNameIn(tagNames);
            List<String> errors = new ArrayList<>();

            tagNames.forEach(tagName -> {
                boolean isPresent = tagEntities.stream().map(TagEntity::getTagName).anyMatch(s -> s.equals(tagName));
                if (!isPresent){
                    errors.add(tagName);
                }
            });
            if (!CollectionUtils.isEmpty(errors)){
                throw new ValidationException(ValidationException.ValidationError.VALIDATION_ERROR, "Invalid tags: " + String.join(", ", errors));
            }
        }
    }


    public void validate(ProductRequestDto request) throws ValidationException {
        if (request == null) {
            throw new ValidationException(ValidationException.ValidationError.EMPTY_REQUEST_BODY);
        }
        // validate fields
        validateProductName(request.getProductName());


        // DB Validation:
        validateDuplicateProductFromDB(request.getStoreId(), request.getProductName());
        validateTagsFromDB(request.getTags());
    }



}
