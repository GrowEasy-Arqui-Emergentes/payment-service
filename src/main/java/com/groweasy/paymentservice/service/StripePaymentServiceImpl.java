package com.groweasy.paymentservice.service;


import com.groweasy.paymentservice.domain.model.StripeChargeDto;
import com.groweasy.paymentservice.domain.model.StripeTokenDto;
import com.groweasy.paymentservice.domain.service.StripePaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Slf4j
@Service
public class StripePaymentServiceImpl implements StripePaymentService {

    private final String apiKey = "pk_test_51QG6UWGMg0Arh6GLfDhcxXdmQboINqANaN2JuxZNOZasgaBeupRl2zAo659Kz97hSQOK2oINBXB869rjKzdgHCYE00QU9IVOcW";
    private final String secretApiKey = "sk_test_51QG6UWGMg0Arh6GLS4nLgWWmeJtDfaWDgejrMvnDMtfUIyXuGOCSwVvqPn698gQA95hE2bZie9Uaewo9wUlUqv1000jFte4m9w";
    @Override
    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    @Override
    public StripeTokenDto createCardToken(StripeTokenDto model) {
        Stripe.apiKey = apiKey;
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("card", card);

            Token token = Token.create(params);
            System.out.println("Token creado: " + token.getId());

            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }

            return model;
        } catch (StripeException e) {
            log.error("Error al crear el token de la tarjeta con Stripe", e);
            throw new RuntimeException("Error al crear el token de la tarjeta con Stripe: " + e.getMessage(), e);
        }
    }

    @Override
    public StripeChargeDto charge(StripeChargeDto chargeRequest) {
        Stripe.apiKey = secretApiKey;
        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }


    }
}
