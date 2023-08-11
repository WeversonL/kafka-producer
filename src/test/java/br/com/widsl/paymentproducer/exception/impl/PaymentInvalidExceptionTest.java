package br.com.widsl.paymentproducer.exception.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("PaymentInvalidException Test")
class PaymentInvalidExceptionTest {

    @Test
    @DisplayName("Validate that the PaymentInvalidException exception is being instantiated correctly")
    void testPaymentInvalidException() {
        PaymentInvalidException exception = new PaymentInvalidException("message");
        assertEquals("message", exception.getMessage());
        assertTrue(exception instanceof PaymentInvalidException);
    }

}