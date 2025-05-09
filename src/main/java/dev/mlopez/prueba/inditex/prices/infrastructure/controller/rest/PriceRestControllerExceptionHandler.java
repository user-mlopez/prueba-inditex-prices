package dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest;

import dev.mlopez.prueba.inditex.prices.domain.exceptions.PriceNotFoundException;
import dev.mlopez.prueba.inditex.prices.domain.exceptions.ValidationException;
import dev.mlopez.prueba.inditex.prices.infrastructure.controller.rest.model.HttpErrorInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;


@RestControllerAdvice
class PriceRestControllerExceptionHandler {

    private final PriceRestMessageSource messageSource;

    public PriceRestControllerExceptionHandler(PriceRestMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public @ResponseBody HttpErrorInfo handleInvalidInputException(HttpServletRequest request, ValidationException ex) {

        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, messageSource.getMessage(ex.getMessage()));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(PriceNotFoundException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundExceptions(HttpServletRequest request, PriceNotFoundException ex) {

        return createHttpErrorInfo(NOT_FOUND, request, messageSource.getMessage(ex.getMessage()));
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public @ResponseBody HttpErrorInfo handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {

        String errorMessage = messageSource.getMessage("error.paramter.invalid",
                new Object[]{
                        (ex.getValue() != null ? ex.getValue().toString() : ""),
                        ex.getName(),
                        (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "")});

        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, errorMessage);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public @ResponseBody HttpErrorInfo handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {

        String errorMessage = messageSource.getMessage("error.paramter.required",
                new Object[]{ex.getParameterName(), ex.getParameterType()});

        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, errorMessage);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest request, String message) {

        return new HttpErrorInfo(httpStatus, request.getRequestURL().toString(), message);
    }
}
