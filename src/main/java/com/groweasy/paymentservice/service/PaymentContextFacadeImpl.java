package com.groweasy.paymentservice.service;



import com.groweasy.paymentservice.api.internal.PaymentContextFacade;
import com.groweasy.paymentservice.domain.model.Payment;
import com.groweasy.paymentservice.domain.service.PaymentService;

import java.util.List;

public class PaymentContextFacadeImpl implements PaymentContextFacade {

    private final PaymentService paymentService;

    public PaymentContextFacadeImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentService.getAll();
    }
}
