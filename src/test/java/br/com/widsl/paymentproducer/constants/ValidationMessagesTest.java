package br.com.widsl.paymentproducer.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ValidationMessages Test")
class ValidationMessagesTest {

    @Test
    void testFieldIsRequired() {
        assertEquals("The field '%s' is required", ValidationMessages.FIELD_IS_REQUIRED);
    }

}
