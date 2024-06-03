package com.assignment.parkinglot.model;

import com.assignment.parkinglot.entity.PaymentEntity;


public class CashPayment extends Payment {
    public CashPayment(PaymentEntity payment) {
        super(payment);
    }

    @Override
    public void processPayment() {
        payment.setAmount(payment.getAmount());
        payment.setPaymentMethod("CASH");
    }
}