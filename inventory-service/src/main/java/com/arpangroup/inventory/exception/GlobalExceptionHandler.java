package com.arpangroup.inventory.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ApiError> handleIdNotFoundException(IdNotFoundException e, WebRequest request){
        final ApiError apiError = new ApiError(e.getErrorMessage(), request);
        return new ResponseEntity<>(apiError,null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException e, WebRequest request){
        List<ErrorMessage> errors = e.getErrors();
        ErrorMessage errorMessage = e.getErrorMessage();
        boolean isEmptyErrorMessage = StringUtils.isEmpty(errorMessage.getMessage()) && StringUtils.isEmpty(errorMessage.getError());

        final ApiError apiError;
        if (errors != null && errors.size() > 0){
            if(!isEmptyErrorMessage){
                errors.add(errorMessage);
            }
            apiError = new ApiError(errors, request);
        }else{
            apiError = new ApiError(e.getErrorMessage(), request);
        }

        return new ResponseEntity<>(apiError, null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        final String exceptionResponse = String.format("Invalid input parameters: %s\n", exception.getMessage());
        final ErrorMessage errorMessage = new ErrorMessage("", exceptionResponse);
        final ApiError apiError = new ApiError(errorMessage, request);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        final ApiError apiError = new ApiError(null, ex.getMessage(), request);
//        return new ResponseEntity<>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
//    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request){
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value()+"", e.getMessage(), request);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleCommonException(Exception e, WebRequest request){
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value()+"", e.getMessage(), request);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(MultipartException.class)
//    public String handleMultipartError(MultipartException e, RedirectAttributes redirectAttributes) {
//
//        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
//        return "redirect:/uploadStatus";
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<ApiError> handleConstraintViolationException1(MethodArgumentNotValidException ex, WebRequest request){
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        List<ErrorMessage> errorMessages = errors.stream().map(s -> new ErrorMessage(HttpStatus.BAD_REQUEST.value()+"", s)).collect(Collectors.toList());

        //String message = ex.getLocalizedMessage();
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ApiError apiError = new ApiError(errorMessages, request);
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
