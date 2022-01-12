package com.arpangroup.inventory.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidationException extends BaseException {
    private List<ErrorMessage> errors;

    public ValidationException(ValidationError validationError){
        super(validationError.value(), validationError.getReasonPhrase());
    }

    public ValidationException(ValidationError validationError, String customMessage){
        super(validationError.value(), customMessage);
    }

    public ValidationException(ErrorField errorField){
        super(errorField.getFieldName(), errorField.getErrorMessage());
    }

    public ValidationException(List<ErrorField> errorFields){
        super();
        if (errorFields != null){
            errors = errorFields.stream().map(e -> new ErrorMessage(e.getFieldName(), e.getErrorMessage())).collect(Collectors.toList());
        }
    }


    public List<ErrorMessage> getErrors() {
        return errors;
    }


    public enum ValidationError {
        VALIDATION_ERROR("V100", "Exception occured"),
        EMPTY_REQUEST_BODY("V101", "Empty request body"),
        INVALID_ID("V102", "Invalid id value"),
        INVALID_FIELD("V103", "Invalid field"),
        DUPLICATE_CATEGORY("V104", "category_name already exist"),
        INTERNAL_SERVER_ERROR("V105", "internal server error"),
        DATA_INSERT_ERROR("V106", "data insert exception"),
        ILLEGAL_ARGUMENT("V107", "illegal argument exception"),
        DUPLICATE_PRODUCT("V108", "product_name already exist"),
        DUPLICATE_TAG_NAME("V109", "tag_name already exist"),
        INVALID_STORE_CHARGE_TYPE("V110", "invalid storeChargeType"),
        INVALID_DELIVERY_CHARGE_TYPE("V111", "invalid deliveryChargeType");

        private final String value;
        private final String reasonPhrase;

        ValidationError(String value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }
        public String value() {
            return value;
        }
        public String getReasonPhrase() {
            return this.reasonPhrase;
        }
    }

    public static class ErrorField {
        private String fieldName;
        private String errorMessage;
        public ErrorField(@NotEmpty String fieldName, @NotEmpty String errorMessage){
            this.fieldName = fieldName;
            this.errorMessage = errorMessage;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
