package br.com.widsl.paymentproducer.domain.dto;

import br.com.widsl.paymentproducer.enums.PaymentMethod;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PaymentRequest Test")
class PaymentRequestTest {

    @Test
    @DisplayName("Testing PaymentMethod Json Deserialization with valid data")
    void testJsonDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"customerName\":\"John Doe\",\"paymentMethod\":\"C\",\"paymentAmount\":100.0}";

        PaymentRequest paymentDTO = objectMapper.readValue(json, PaymentRequest.class);

        assertEquals(PaymentMethod.CREDIT, paymentDTO.getPaymentMethod());
    }

    @Test
    @DisplayName("Testing PaymentMethod Json Deserialization with invalid data")
    void testJsonDeserializationInvalid() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"customerName\":\"John Doe\",\"paymentMethod\":\"X\",\"paymentAmount\":100.0}";

        assertThatThrownBy(() -> objectMapper.readValue(json, PaymentRequest.class))
                .isInstanceOf(JsonMappingException.class);

    }

    @Test
    @DisplayName("Should correctly convert PaymentRequest object to string")
    void toStringCorrectlyConvertsPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest("John Doe", PaymentMethod.CREDIT, 100.0);

        String expectedString = "PaymentRequest{" +
                "customerName='John Doe'" +
                ", paymentMethod=CREDIT" +
                ", paymentAmount=100.0" +
                "}";

        assertEquals(expectedString, paymentRequest.toString());
    }

    @Test
    @DisplayName("Should set the payment amount correctly")
    void testSetPaymentAmount() {
        PaymentRequest paymentRequest = new PaymentRequest("John Doe", PaymentMethod.CREDIT, 100.0);

        paymentRequest.setPaymentAmount(600.0);

        assertEquals(600.0, paymentRequest.getPaymentAmount());
    }

    @Test
    @DisplayName("Should return the correct payment amount when getPaymentAmount is called")
    void testGetPaymentAmount() {
        PaymentRequest paymentRequest = new PaymentRequest("John Doe", PaymentMethod.CREDIT, 400.0);

        assertEquals(400.0, paymentRequest.getPaymentAmount());
    }

    @Test
    @DisplayName("Should set the payment method name correctly")
    void testSetPaymentMethod() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setPaymentMethod(PaymentMethod.DEBIT);

        assertEquals(PaymentMethod.DEBIT, paymentRequest.getPaymentMethod());
    }

    @Test
    @DisplayName("Should return the correct payment method when getPaymentMethod is called")
    void testGetPaymentMethod() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setPaymentMethod(PaymentMethod.DEBIT);

        assertEquals(PaymentMethod.DEBIT, paymentRequest.getPaymentMethod());
    }

    @Test
    @DisplayName("Should set the customer name correctly")
    void setCustomerName() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCustomerName("Jane Smith");

        assertEquals("Jane Smith", paymentRequest.getCustomerName());
    }

    @Test
    @DisplayName("Should return the correct customer name when getCustomerName is called")
    void getCustomerNameReturnsCorrectName() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setCustomerName("John Doe");

        String customerName = paymentRequest.getCustomerName();

        assertEquals("John Doe", customerName);
    }

    @Test
    @DisplayName("Should return null when the customer name is not set and getCustomerName is called")
    void getCustomerNameReturnsNullWhenNameNotSet() {
        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setCustomerName(null);

        assertNull(paymentRequest.getCustomerName());
    }

    @Test
    @DisplayName("Should return instance correct of PaymentRequest")
    void testValidationEmptyConstructor() {
        PaymentRequest paymentRequest = new PaymentRequest();
        assertNotNull(paymentRequest);
    }

    @Test
    @DisplayName("Should return instance correct of PaymentRequest with data")
    void testValidation() {
        PaymentRequest paymentRequest = new PaymentRequest("John Doe", PaymentMethod.CREDIT, 100.0);

        assertEquals("John Doe", paymentRequest.getCustomerName());
        assertEquals(PaymentMethod.CREDIT, paymentRequest.getPaymentMethod());
        assertEquals(100.0, paymentRequest.getPaymentAmount());
    }

}
