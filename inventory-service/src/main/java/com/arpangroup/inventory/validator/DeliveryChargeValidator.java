package com.arpangroup.inventory.validator;

import com.arpangroup.inventory.entity.DeliveryChargeEntity;
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
public class DeliveryChargeValidator {


    public boolean isValid(DeliveryChargeEntity entity) throws ValidationException {
        log.info("inside isValid() to validate the DeliveryChargeEntity");
        if (entity == null) {
            throw new ValidationException(new ErrorField("deliveryCharge", "deliveryCharge should not be empty"));
        }
        if (entity.getDeliveryChargeType() == null ) {
            throw new ValidationException(new ErrorField("deliveryChargeType", "deliveryChargeType should not be null"));
        }
        switch (entity.getDeliveryChargeType()){
            case FIXED:
                if (entity.getFixedDeliveryCharge() == null) {
                    throw new ValidationException(new ErrorField("fixedDeliveryCharge", "fixedDeliveryCharge should not be null for FIXED type charge"));
                }
                if (entity.getFixedDeliveryCharge() <= 0f) {
                    throw new ValidationException(new ErrorField("fixedDeliveryCharge", "fixedDeliveryCharge is not a valid amount"));
                }
                return true;
            case DYNAMIC:
                List<ErrorField> errorFields = new ArrayList<>();
                if (entity.getBaseDeliveryCharge() == null) {
                    errorFields.add(new ErrorField("baseDeliveryCharge", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getBaseDeliveryDistance() == null) {
                    errorFields.add(new ErrorField("baseDeliveryDistance", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getExtraDeliveryCharge() == null) {
                    errorFields.add(new ErrorField("extraDeliveryCharge", "should not be null for DYNAMIC type charge"));
                }
                if (entity.getExtraDeliveryDistance() == null) {
                    errorFields.add(new ErrorField("extraDeliveryDistance", "should not be null for DYNAMIC type charge"));
                }

                if (entity.getBaseDeliveryCharge() != null && entity.getBaseDeliveryCharge() <= 0f) {
                    errorFields.add(new ErrorField("baseDeliveryCharge", ""));
                }
                if (entity.getBaseDeliveryDistance() != null && entity.getBaseDeliveryDistance() <= 0f) {
                    errorFields.add(new ErrorField("baseDeliveryDistance", ""));
                }
                if (entity.getExtraDeliveryCharge() != null && entity.getExtraDeliveryCharge() <= 0f) {
                    errorFields.add(new ErrorField("extraDeliveryCharge", ""));
                }
                if (entity.getExtraDeliveryDistance() != null && entity.getExtraDeliveryDistance() <= 0) {
                    errorFields.add(new ErrorField("extraDeliveryDistance", ""));
                }

                if (!CollectionUtils.isEmpty(errorFields)) {
                    throw new ValidationException(errorFields);
                }
                return true;
            default:
                log.error("store charge type not matched");
                throw new ValidationException(ValidationError.INVALID_DELIVERY_CHARGE_TYPE, "invalid deliveryChargeType: " + entity.getDeliveryChargeType());
        }
    }
}
