package com.example.shopit.payment;

public interface PaymentService {

    public PaymentDetail proceedPayment(PaymentDetail paymentDetail);
    public String payuCallback(PaymentCallback paymentResponse);
}
