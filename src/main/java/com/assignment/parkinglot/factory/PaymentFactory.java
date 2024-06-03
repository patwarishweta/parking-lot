package com.assignment.parkinglot.factory;

import com.assignment.parkinglot.entity.PaymentEntity;
import com.assignment.parkinglot.model.CashPayment;
import com.assignment.parkinglot.model.CreditCardPayment;
import com.assignment.parkinglot.model.Payment;

public class PaymentFactory {
    public static Payment createPayment(String type, PaymentEntity payment) {
        switch (type.toUpperCase()) {
            case "CREDIT_CARD":
                return new CreditCardPayment(payment);
            case "CASH":
                return new CashPayment(payment);
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }
    }
}