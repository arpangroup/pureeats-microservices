package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.dto.request.StoreRequest;
import com.arpangroup.inventory.entity.StoreEntity;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.repository.StoreRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StoreValidator {
    private final StoreRepository repository;
    private final StoreChargeValidator storeChargeValidator;
    private final DeliveryChargeValidator deliveryChargeValidator;


    public StoreValidator(StoreRepository repository, StoreChargeValidator storeChargeValidator, DeliveryChargeValidator deliveryChargeValidator) {
        this.repository = repository;
        this.storeChargeValidator = storeChargeValidator;
        this.deliveryChargeValidator = deliveryChargeValidator;
    }

    private void validateStoreOwner(int storeOwnerId) throws ValidationException{
        return;
    }


    private void validateStoreName(String storeName) throws ValidationException{
        if (storeName == null || storeName.equals("")) {
            throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "storeName should not be null or empty");
        }
        if (storeName.length() < 4) {
            throw new ValidationException(ValidationException.ValidationError.INVALID_FIELD, "storeName should be minimum 4 characters long");
        }
    }

    private void validateStoreType(String storeType) {

    }

    private void validateDuplicateStoreFromDB(int storeOwnerId, String storeName) throws ValidationException{
        boolean isStorePresent = repository.findByStoreOwnerIdAndStoreNameIgnoreCase(storeOwnerId, storeName).isPresent();
        if (isStorePresent) {
            throw new ValidationException(ValidationException.ValidationError.DUPLICATE_TAG_NAME, storeName +" already exist");
        }
    }

    private void validateStoreOwnerRoleAndAccess(int storeOwnerId)throws ValidationException{

    }

    private void validateDuplicateStoreOwner(int storeOwnerId)throws ValidationException{

    }

    public void validate(StoreEntity request) throws ValidationException {
        if (request == null) {
            throw new ValidationException(ValidationException.ValidationError.EMPTY_REQUEST_BODY);
        }
        // validate fields
        validateStoreOwner(request.getStoreOwnerId());
        validateStoreName(request.getStoreName());
        //if (request.getContactNumber() != null) validateContactNumber(request.getContactNumber());
        //validateStoreType(request.getStore);
        storeChargeValidator.isValid(request.getStoreCharge());
        deliveryChargeValidator.isValid(request.getDeliveryCharge());


        // DB Validation:
        validateStoreOwnerRoleAndAccess(request.getStoreOwnerId());
        validateDuplicateStoreOwner(request.getStoreOwnerId());
        validateDuplicateStoreFromDB(request.getStoreOwnerId(), request.getStoreName());

    }
}
