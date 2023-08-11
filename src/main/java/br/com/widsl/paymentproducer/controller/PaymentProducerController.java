package br.com.widsl.paymentproducer.controller;

import br.com.widsl.paymentproducer.domain.dto.PaymentDTO;
import br.com.widsl.paymentproducer.domain.dto.PaymentRequest;
import br.com.widsl.paymentproducer.service.PaymentProducerService;
import br.com.widsl.paymentproducer.service.impl.PaymentProducerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentProducerController {

    private final PaymentProducerService paymentProducerService;

    public PaymentProducerController(PaymentProducerServiceImpl paymentService) {
        this.paymentProducerService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@RequestBody @Valid PaymentRequest paymentRequest) {
        PaymentDTO response = paymentProducerService.send(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
