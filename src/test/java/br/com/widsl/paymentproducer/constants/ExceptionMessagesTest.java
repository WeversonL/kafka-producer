package br.com.widsl.paymentproducer.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ExceptionMessages Test")
class ExceptionMessagesTest {

    @Test
    void testPaymentProcessException() {
        assertEquals("Payment Process Exception", ExceptionMessages.PAYMENT_PROCESS_EXCEPTION);
    }

    @Test
    void testPaymentProcessExceptionDescription() {
        assertEquals("There was an error processing the payment",
                ExceptionMessages.PAYMENT_PROCESS_EXCEPTION_DESCRIPTION);
    }

    @Test
    void testBadRequestException() {
        assertEquals("Bad Request", ExceptionMessages.BAD_REQUEST_EXCEPTION);
    }

    @Test
    void testBadRequestExceptionDescription() {
        assertEquals("There were validation errors in the submitted fields",
                ExceptionMessages.BAD_REQUEST_EXCEPTION_DESCRIPTION);
    }
}
