package br.com.widsl.paymentproducer.constants;

public final class ExceptionMessages {

    private ExceptionMessages() {
    }

    public static final String PAYMENT_PROCESS_EXCEPTION = "Payment Process Exception";
    public static final String PAYMENT_PROCESS_EXCEPTION_DESCRIPTION = "There was an error processing the payment";
    public static final String BAD_REQUEST_EXCEPTION = "Bad Request";
    public static final String BAD_REQUEST_EXCEPTION_DESCRIPTION = "There were validation errors in the submitted fields";
    public static final String INVALID_PAYMENT_EXCEPTION = "Invalid payment";

}
