package br.com.widsl.paymentproducer.service;

import br.com.widsl.paymentproducer.domain.dto.PaymentDTO;
import br.com.widsl.paymentproducer.domain.dto.PaymentRequest;

public interface PaymentProducerService {

    PaymentDTO send(PaymentRequest paymentRequest);

}
