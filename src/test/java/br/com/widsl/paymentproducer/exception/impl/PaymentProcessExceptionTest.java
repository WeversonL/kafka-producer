package br.com.widsl.paymentproducer.exception.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("PaymentProcessException Test")
class PaymentProcessExceptionTest {

    @Test
    @DisplayName("Validate that the PaymentProcessException exception is being instantiated correctly")
    void testPaymentProcessException() {
        PaymentProcessException exception = new PaymentProcessException();
        assertNull(exception.getMessage());
        assertTrue(exception instanceof PaymentProcessException);
    }

}