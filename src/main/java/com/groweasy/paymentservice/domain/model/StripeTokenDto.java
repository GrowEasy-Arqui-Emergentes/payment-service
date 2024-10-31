package com.groweasy.paymentservice.domain.model;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class StripeTokenDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    @Nullable
    private String token;
    private String username;
    @Nullable
    private boolean success;
}
