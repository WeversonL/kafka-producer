package br.com.widsl.paymentproducer.controller;

import br.com.widsl.paymentproducer.domain.dto.PaymentRequest;
import br.com.widsl.paymentproducer.enums.PaymentMethod;
import br.com.widsl.paymentproducer.service.impl.PaymentProducerServiceImpl;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentProducerController Test")
class PaymentProducerControllerTest {

    @Mock
    private PaymentProducerServiceImpl paymentProducerService;

    @InjectMocks
    private PaymentProducerController paymentProducerController;

    @Test
    @DisplayName("Should throw an exception when the paymentDTO is not valid")
    void sendMessageWhenPaymentDtoIsNotValidThenThrowException() {
        PaymentRequest paymentRequest = new PaymentRequest("", PaymentMethod.CREDIT, 100.0);

        when(paymentProducerController.sendMessage(any(PaymentRequest.class))).thenThrow(ValidationException.class);

        assertThatThrownBy(() -> paymentProducerController.sendMessage(paymentRequest))
                .isInstanceOf(ValidationException.class);

    }

    @Test
    @DisplayName("Should send the message when the paymentDTO is valid")
    void sendMessageWhenPaymentDtoIsValid() {
        PaymentRequest paymentRequest = new PaymentRequest("John Doe", PaymentMethod.CREDIT, 100.0);

        paymentProducerController.sendMessage(paymentRequest);

        verify(paymentProducerService, times(1)).send(paymentRequest);
    }

}