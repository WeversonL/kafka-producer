package br.com.widsl.paymentproducer.exception.advice;

import br.com.widsl.paymentproducer.constants.ExceptionMessages;
import br.com.widsl.paymentproducer.exception.impl.PaymentInvalidException;
import br.com.widsl.paymentproducer.exception.impl.PaymentProcessException;
import br.com.widsl.paymentproducer.exception.model.ApiErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DefaultControllerAdvice Test")
class DefaultControllerAdviceTest {

    @InjectMocks
    private DefaultControllerAdvice defaultControllerAdvice;

    private BindingResult createBindingResult() {
        return new BeanPropertyBindingResult(null, "objectName");
    }

    @Test
    @DisplayName("Should return ApiErrorResponse with BAD_REQUEST status and validation errors when MethodArgumentNotValidException is thrown")
    void handleMethodArgumentNotValidWhenExceptionIsThrown() {

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException((MethodParameter) null,
                createBindingResult());
        exception.addError(new FieldError("fieldName", "rejectedValue", "error message"));

        ResponseEntity<ApiErrorResponse> response = defaultControllerAdvice.handleMethodArgumentNotValid(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals(ExceptionMessages.BAD_REQUEST_EXCEPTION, Objects.requireNonNull(response.getBody()).getMessage());
        assertEquals(ExceptionMessages.BAD_REQUEST_EXCEPTION_DESCRIPTION,
                Objects.requireNonNull(response.getBody()).getDescription());
        assertNotNull(Objects.requireNonNull(response.getBody()).getErrors());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getErrors().size());
        assertEquals("error message", Objects.requireNonNull(response.getBody()).getErrors().get(0).getDescription());
    }

    @Test
    @DisplayName("Should return a Bad Request response entity when PaymentInvalidException is thrown")
    void handlePaymentInvalidExceptionException() {

        PaymentInvalidException exception = new PaymentInvalidException("invalid");

        ResponseEntity<ApiErrorResponse> response = defaultControllerAdvice.handlePaymentInvalidException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());

        ApiErrorResponse apiErrorResponse = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(apiErrorResponse).getCode());
        assertEquals(ExceptionMessages.INVALID_PAYMENT_EXCEPTION, apiErrorResponse.getMessage());
        assertEquals("invalid", apiErrorResponse.getDescription());
        assertTrue(apiErrorResponse.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Should return a Internal Server Error response entity when PaymentProcessException is thrown")
    void handlePaymentProcessExceptionException() {

        PaymentProcessException exception = new PaymentProcessException();

        ResponseEntity<ApiErrorResponse> response = defaultControllerAdvice.handlePaymentProcessException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());

        ApiErrorResponse apiErrorResponse = response.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Objects.requireNonNull(apiErrorResponse).getCode());
        assertEquals(ExceptionMessages.PAYMENT_PROCESS_EXCEPTION, apiErrorResponse.getMessage());
        assertEquals(ExceptionMessages.PAYMENT_PROCESS_EXCEPTION_DESCRIPTION, apiErrorResponse.getDescription());
        assertTrue(apiErrorResponse.getErrors().isEmpty());
    }

}