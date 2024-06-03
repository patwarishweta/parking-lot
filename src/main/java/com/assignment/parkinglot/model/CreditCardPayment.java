package com.assignment.parkinglot.model;

import com.assignment.parkinglot.entity.PaymentEntity;

public class CreditCardPayment extends Payment {
    public CreditCardPayment(PaymentEntity payment) {
        super(payment);
    }

    @Override
    public void processPayment() {
        payment.setAmount(payment.getAmount());
        payment.setPaymentMethod("CREDIT_CARD");
    }
}