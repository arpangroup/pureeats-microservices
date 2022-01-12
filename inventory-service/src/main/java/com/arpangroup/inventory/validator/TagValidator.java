package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.request.TagRequest;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.repository.CategoryRepository;
import com.arpangroup.inventory.repository.TagRepository;
import org.springframework.stereotype.Component;

@Component
public class TagValidator {
    private final TagRepository repository;

    public TagValidator(TagRepository repository) {
        this.repository = repository;
    }


    public void validate(TagRequest request) throws ValidationException {
        if (request.getTagName() == null || request.getTagName().equals("")) throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "tagName should not be null or empty");
        if (request.getTagName().length() < 4) throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "tagName should be minimum 3 characters long");
        boolean isTagPresent = repository.findByTagNameIgnoreCase(request.getTagName()).isPresent();
        if (isTagPresent) {
            throw new ValidationException(ValidationException.ValidationError.DUPLICATE_TAG_NAME, request.getTagName() +" already exist");
        }
    }
}
