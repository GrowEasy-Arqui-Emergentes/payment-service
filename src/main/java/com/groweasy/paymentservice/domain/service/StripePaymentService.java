package com.groweasy.paymentservice.domain.service;


import com.groweasy.paymentservice.domain.model.StripeChargeDto;
import com.groweasy.paymentservice.domain.model.StripeTokenDto;

public interface StripePaymentService {
    void init();
    StripeTokenDto createCardToken(StripeTokenDto model);
    StripeChargeDto charge(StripeChargeDto chargeRequest);
}
