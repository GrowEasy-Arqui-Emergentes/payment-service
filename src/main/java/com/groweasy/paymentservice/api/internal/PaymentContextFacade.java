package com.groweasy.paymentservice.api.internal;


import com.groweasy.paymentservice.domain.model.Payment;

import java.util.List;

public interface PaymentContextFacade {
    List<Payment> getAllPayments();
    
}
