package br.com.widsl.paymentproducer.exception.advice;

import br.com.widsl.paymentproducer.constants.ExceptionMessages;
import br.com.widsl.paymentproducer.exception.impl.PaymentInvalidException;
import br.com.widsl.paymentproducer.exception.impl.PaymentProcessException;
import br.com.widsl.paymentproducer.exception.model.ApiErrorResponse;
import br.com.widsl.paymentproducer.exception.model.ErrorValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PaymentProcessException.class)
    public ResponseEntity<ApiErrorResponse> handlePaymentProcessException(PaymentProcessException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiErrorResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ExceptionMessages.PAYMENT_PROCESS_EXCEPTION)
                        .description(ExceptionMessages.PAYMENT_PROCESS_EXCEPTION_DESCRIPTION)
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PaymentInvalidException.class)
    public ResponseEntity<ApiErrorResponse> handlePaymentInvalidException(PaymentInvalidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(ExceptionMessages.INVALID_PAYMENT_EXCEPTION)
                        .description(exception.getMessage())
                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ExceptionMessages.BAD_REQUEST_EXCEPTION)
                .description(ExceptionMessages.BAD_REQUEST_EXCEPTION_DESCRIPTION)
                .build();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String description = Objects.requireNonNull(error.getDefaultMessage()).formatted(error.getField());
            apiErrorResponse.addError(new ErrorValidation(description));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

}
