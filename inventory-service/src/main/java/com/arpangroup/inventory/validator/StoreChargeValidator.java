package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.entity.StoreChargeEntity;
import com.arpangroup.inventory.exception.ValidationException;
import com.arpangroup.inventory.exception.ValidationException.ErrorField;
import com.arpangroup.inventory.exception.ValidationException.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class StoreChargeValidator {


    private Float fixedStoreCharge;

    public boolean isValid(StoreChargeEntity entity) throws ValidationException {
        log.info("inside isValid() to validate the StoreChargeEntity");
        if (entity == null) {
            throw new ValidationException(new ErrorField("storeCharge", "storeCharge should not be empty"));
        }
        if (entity.getStoreChargeType() == null) {
            throw new ValidationException(new ErrorField("storeChargeType", "storeChargeType should not be null"));
        }

        switch (entity.getStoreChargeType()) {
            case FIXED:
                if (entity.getFixedStoreCharge() == null) {
                    throw new ValidationException(new ErrorField("fixedStoreCharge", "fixedStoreCharge should not be null for FIXED type charge"));
                }
                if (entity.getFixedStoreCharge() <= 0f) {
                    throw new ValidationException(new ErrorField("fixedStoreCharge", "fixedStoreCharge is not a valid amount"));
                }
                return true;
            case PERCENTAGE:
                if (entity.getFixedCommissionPercentage() == null) {
                    throw new ValidationException(new ErrorField("fixedCommissionPercentage", "fixedCommissionPercentage  should not be null for PERCENTAGE type charge"));
                }
                if (entity.getFixedCommissionPercentage() <= 0f) {
                    throw new ValidationException(new ErrorField("fixedCommissionPercentage", "fixedCommissionPercentage is not a valid amount"));
                }
                return true;
            case DYNAMIC:
                List<ErrorField> errorFields = new ArrayList<>();
                if (entity.getBaseCommissionPercentage() == null) {
                    errorFields.add(new ErrorField("baseCommissionPercentage", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getBaseOrderAmount() == null) {
                    errorFields.add(new ErrorField("baseOrderAmount", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getBaseWeight() == null) {
                    errorFields.add(new ErrorField("baseWeight", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getExtraCommissionPercentage() == null){
                    errorFields.add(new ErrorField("extraCommissionPercentage", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getExtraOrderAmount() == null) {
                    errorFields.add(new ErrorField("extraOrderAmount", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getExtraWeight() == null) {
                    errorFields.add(new ErrorField("extraWeight", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getBaseCommissionPercentage() != null && entity.getBaseCommissionPercentage() <= 0f) {
                    errorFields.add(new ErrorField("baseCommissionPercentage", "should be a valid positive amount"));
                }
                if (entity.getBaseOrderAmount() != null && entity.getBaseOrderAmount() <= 0f) {
                    errorFields.add(new ErrorField("baseOrderAmount", "should be a valid positive amount"));
                }
                if (entity.getBaseWeight() != null && entity.getBaseWeight() <= 0f) {
                    errorFields.add(new ErrorField("baseWeight", "should be a valid positive amount"));
                }
                if (entity.getExtraCommissionPercentage() != null && entity.getExtraCommissionPercentage() <= 0f) {
                    errorFields.add(new ErrorField("extraCommissionPercentage", "should be a valid positive amount"));
                }
                if (entity.getExtraOrderAmount() != null && entity.getExtraOrderAmount() <= 0f) {
                    errorFields.add(new ErrorField("extraOrderAmount", "should be a valid positive amount"));
                }
                if (entity.getExtraWeight() != null && entity.getExtraWeight() <= 0f) {
                    errorFields.add(new ErrorField("extraWeight", "should be a valid positive amount"));
                }
                if (!CollectionUtils.isEmpty(errorFields)) {
                    throw new ValidationException(errorFields);
                }
                return true;
            default:
                log.error("store charge type not matched");
                throw new ValidationException(ValidationError.INVALID_STORE_CHARGE_TYPE, "invalid storeChargeType: " + entity.getStoreChargeType());
        }
    }

}
