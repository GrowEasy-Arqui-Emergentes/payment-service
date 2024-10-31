package com.groweasy.paymentservice.api.rest;


import com.groweasy.paymentservice.domain.model.StripeChargeDto;
import com.groweasy.paymentservice.domain.model.StripeTokenDto;
import com.groweasy.paymentservice.domain.service.StripePaymentService;
import com.groweasy.paymentservice.mapping.StripeChargeMapper;
import com.groweasy.paymentservice.mapping.StripePaymentMapper;
import com.groweasy.paymentservice.resource.CreateStripeChargeResource;
import com.groweasy.paymentservice.resource.CreateStripeTokenResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/stripe-payment", produces = "application/json")
@Tag(name = "Stripe", description = "Pay using Stripe")
public class StripePaymentController {

    private final StripePaymentService stripePaymentService;
    private final StripePaymentMapper mapper;
    private final StripeChargeMapper chargeMapper;

    public StripePaymentController(StripePaymentService stripePaymentService, StripePaymentMapper mapper, StripeChargeMapper chargeMapper) {
        this.stripePaymentService = stripePaymentService;
        this.mapper = mapper;
        this.chargeMapper = chargeMapper;
    }

    @PostMapping("/card/token")
    public StripeTokenDto createCardToken(@RequestBody CreateStripeTokenResource model) {

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(model);
        return stripePaymentService.createCardToken(mapper.toModel(model));
    }

    @PostMapping("/charge")
    public StripeChargeDto charge(@RequestBody CreateStripeChargeResource model) {

        return stripePaymentService.charge(chargeMapper.toModel(model));
    }
}
