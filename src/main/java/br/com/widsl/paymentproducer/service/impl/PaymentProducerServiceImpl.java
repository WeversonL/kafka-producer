package br.com.widsl.paymentproducer.service.impl;

import br.com.widsl.paymentproducer.domain.dto.PaymentDTO;
import br.com.widsl.paymentproducer.domain.dto.PaymentRequest;
import br.com.widsl.paymentproducer.enums.PaymentStatus;
import br.com.widsl.paymentproducer.exception.impl.PaymentProcessException;
import br.com.widsl.paymentproducer.service.PaymentProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentProducerServiceImpl implements PaymentProducerService {

    private final Logger logger = LoggerFactory.getLogger(PaymentProducerServiceImpl.class);

    private final KafkaTemplate<String, PaymentDTO> kafkaTemplate;
    private final String topic;

    public PaymentProducerServiceImpl(KafkaTemplate<String, PaymentDTO> kafkaTemplate,
                                      @Value("${topic.default-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public PaymentDTO send(PaymentRequest paymentRequest) {

        try {

            PaymentDTO paymentDTO = new PaymentDTO.Builder()
                    .id(UUID.randomUUID())
                    .customerName(paymentRequest.getCustomerName())
                    .paymentMethod(paymentRequest.getPaymentMethod())
                    .paymentAmount(paymentRequest.getPaymentAmount())
                    .status(PaymentStatus.ANALYSIS)
                    .build();

            kafkaTemplate.send(topic, paymentDTO);

            return paymentDTO;

        } catch (Exception e) {
            logger.error("Error to process payment -> {}", paymentRequest);
            throw new PaymentProcessException();
        }

    }

}
