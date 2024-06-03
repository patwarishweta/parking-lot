package com.assignment.parkinglot.model;

import com.assignment.parkinglot.entity.PaymentEntity;

public abstract class Payment {
    protected PaymentEntity payment;

    public Payment(PaymentEntity payment) {
        this.payment = payment;
    }

    public abstract void processPayment();
}