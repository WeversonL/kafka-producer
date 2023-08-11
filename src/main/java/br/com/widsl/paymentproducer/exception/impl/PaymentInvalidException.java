package br.com.widsl.paymentproducer.exception.impl;

public class PaymentInvalidException extends RuntimeException {
    public PaymentInvalidException(String message) {
        super(message);
    }
}
