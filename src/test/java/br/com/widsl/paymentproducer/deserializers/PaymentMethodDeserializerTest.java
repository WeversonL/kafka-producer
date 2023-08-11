package br.com.widsl.paymentproducer.deserializers;

import br.com.widsl.paymentproducer.enums.PaymentMethod;
import br.com.widsl.paymentproducer.exception.impl.PaymentInvalidException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("PaymentMethodDeserializer Test")
class PaymentMethodDeserializerTest {

    @Test
    @DisplayName("Should return DEBIT when the input is 'D' or 'd'")
    void deserializeWhenInputIsDThenReturnDebit() throws IOException {
        JsonParser parser = mock(JsonParser.class);
        DeserializationContext context = mock(DeserializationContext.class);
        PaymentMethodDeserializer deserializer = new PaymentMethodDeserializer();

        when(parser.getText()).thenReturn("D");

        PaymentMethod result = deserializer.deserialize(parser, context);

        assertEquals(PaymentMethod.DEBIT, result);
    }

    @Test
    @DisplayName("Should return CREDIT when the input is 'C' or 'c'")
    void deserializeWhenInputIsCThenReturnCredit() throws IOException {
        JsonParser parser = mock(JsonParser.class);
        DeserializationContext context = mock(DeserializationContext.class);
        PaymentMethodDeserializer deserializer = new PaymentMethodDeserializer();

        when(parser.getText()).thenReturn("C");

        PaymentMethod result = deserializer.deserialize(parser, context);

        assertEquals(PaymentMethod.CREDIT, result);
    }

    @Test
    @DisplayName("Should throw PaymentInvalidException when the input is not 'C', 'c', 'D', or 'd'")
    void deserializeWhenInputIsInvalidThenThrowPaymentInvalidException() throws IOException {
        JsonParser parser = mock(JsonParser.class);
        DeserializationContext context = mock(DeserializationContext.class);
        PaymentMethodDeserializer deserializer = new PaymentMethodDeserializer();

        when(parser.getText()).thenReturn("X");

        assertThrows(PaymentInvalidException.class, () -> {
            deserializer.deserialize(parser, context);
        });
    }

}