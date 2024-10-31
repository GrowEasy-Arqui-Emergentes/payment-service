package com.groweasy.paymentservice.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateStripeTokenResource {
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String username;
}
