package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator{
    private final CategoryRepository repository;

    public CategoryValidator(CategoryRepository repository) {
        this.repository = repository;
    }

    public String validate(String categoryName) throws ValidationException {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "Invalid category_name");
        }
        return categoryName;
    }

    public void validate(CategoryRequestDto request) throws ValidationException {
        if (request == null) throw new ValidationException(ValidationException.ValidationError.EMPTY_REQUEST_BODY);
        validate(request.getCategoryName());

        boolean isCategoryPresent = repository.findByCategoryNameIgnoreCase(request.getCategoryName()).isPresent();
        if (isCategoryPresent) {
            throw new ValidationException(ValidationException.ValidationError.DUPLICATE_CATEGORY, request.getCategoryName() +" already exist");
        }
    }
}
