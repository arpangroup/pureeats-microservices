package com.arpangroup.onboarding.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ApiError apiError = new ApiError(ApiError.ErrorType.MethodArgumentNotValidException, getFieldErrors(ex), request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ApiError apiError = new ApiError(ApiError.ErrorType.BindException, getFieldErrors(ex), request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ApiError> handleIdNotFoundException(IdNotFoundException e, WebRequest request){
        final ApiError apiError = new ApiError(e.getErrorCode(), e.getMessage(), request,  HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,null, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = Arrays.asList(new FieldError(ex.getMessage(), ex.getCause().getMessage()));
        String error = "Malformed JSON request";
        String onlyErrorMessage = null;
        try{
            // truncate the detailed message which contain java related information
            onlyErrorMessage = ex.getLocalizedMessage().substring(0, ex.getLocalizedMessage().lastIndexOf("nested exception is"));
        }catch (Exception e){
            onlyErrorMessage = ex.getMessage();
        }
        final ApiError apiError = new ApiError(error, onlyErrorMessage, request, HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }

    //    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<ApiError> handleValidationException(ValidationException e, WebRequest request){
//        List<ErrorMessage> errors = e.getErrors();
//        ErrorMessage errorMessage = e.getErrorMessage();
//        boolean isEmptyErrorMessage = StringUtils.isEmpty(errorMessage.getMessage()) && StringUtils.isEmpty(errorMessage.getError());
//
//        final ApiError apiError;
//        if (errors != null && errors.size() > 0){
//            if(!isEmptyErrorMessage){
//                errors.add(errorMessage);
//            }
//            apiError = new ApiError(errors, request);
//        }else{
//            apiError = new ApiError(e.getErrorMessage(), request);
//        }
//
//        return new ResponseEntity<>(apiError, null, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ApiError> handleConstraintViolationException1(ConstraintViolationException exception, WebRequest request){
//        final String exceptionResponse = String.format("Invalid input parameters: %s\n", exception.getMessage());
//        final FieldError fieldError = new FieldError("", exceptionResponse);
//        final ApiError apiError = new ApiError(fieldError, request);
//        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }



//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ApiError> handleIllegalArgumentException1(IllegalArgumentException e, WebRequest request){
//        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value()+"", e.getMessage(), request);
//        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
//    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleCommonException1(Exception e, WebRequest request){
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value()+"", e.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler(MultipartException.class)
//    public String handleMultipartError(MultipartException e, RedirectAttributes redirectAttributes) {
//
//        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
//        return "redirect:/uploadStatus";
//    }




    private List<FieldError> getFieldErrors(BindException ex){
        return ex.getBindingResult().getAllErrors().stream()
                .map(e -> new FieldError(((org.springframework.validation.FieldError) e).getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());
    }


}
