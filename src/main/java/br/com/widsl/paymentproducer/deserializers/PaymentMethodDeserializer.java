package br.com.widsl.paymentproducer.deserializers;

import br.com.widsl.paymentproducer.enums.PaymentMethod;
import br.com.widsl.paymentproducer.exception.impl.PaymentInvalidException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PaymentMethodDeserializer extends JsonDeserializer<PaymentMethod> {

    @Override
    public PaymentMethod deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText();
        if ("C".equalsIgnoreCase(value)) {
            return PaymentMethod.CREDIT;
        } else if ("D".equalsIgnoreCase(value)) {
            return PaymentMethod.DEBIT;
        } else {
            throw new PaymentInvalidException("Invalid payment method: " + value);
        }
    }
}
