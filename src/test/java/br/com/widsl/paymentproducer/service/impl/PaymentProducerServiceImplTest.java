package br.com.widsl.paymentproducer.service.impl;

import br.com.widsl.paymentproducer.domain.dto.PaymentDTO;
import br.com.widsl.paymentproducer.domain.dto.PaymentRequest;
import br.com.widsl.paymentproducer.enums.PaymentMethod;
import br.com.widsl.paymentproducer.exception.impl.PaymentProcessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentProducerServiceImplTest {

    @Mock
    private KafkaTemplate<String, PaymentDTO> kafkaTemplate;

    @Mock
    private Logger logger;

    @InjectMocks
    private PaymentProducerServiceImpl paymentProducerService;

    @BeforeEach
    void setup() {
        String topic = "topic";
        paymentProducerService = new PaymentProducerServiceImpl(kafkaTemplate, topic);
    }

    @Test
    @DisplayName("Should send the payment request successfully")
    void sendPaymentRequestSuccessfully() {
        PaymentRequest paymentRequest = new PaymentRequest(
                "John Doe", PaymentMethod.CREDIT, 100.0);

        PaymentDTO response = paymentProducerService.send(paymentRequest);

        verify(kafkaTemplate, times(1)).send(anyString(), any(PaymentDTO.class));
        assertEquals(paymentRequest.getCustomerName(), response.getCustomerName());
        assertEquals(paymentRequest.getPaymentMethod(), response.getPaymentMethod());
        assertEquals(paymentRequest.getPaymentAmount(), response.getPaymentAmount());
        assertNotNull(response.getId());
        assertNotNull(response.getStatus());
    }

    @Test
    @DisplayName("Should return a PaymentProcessException when a service failure occurs")
    void sendPaymentRequestWhenExceptionOccurs() {
        PaymentRequest paymentRequest = new PaymentRequest(
                "John Doe", PaymentMethod.CREDIT, 100.0);

        doThrow(new RuntimeException("Error processing payment"))
                .when(kafkaTemplate).send(anyString(), any(PaymentDTO.class));

        assertThrows(PaymentProcessException.class, () -> paymentProducerService.send(paymentRequest));

    }

}