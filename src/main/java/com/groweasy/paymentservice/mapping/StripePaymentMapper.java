package com.groweasy.paymentservice.mapping;


import com.groweasy.paymentservice.domain.model.StripeTokenDto;
import com.groweasy.paymentservice.resource.CreateStripeTokenResource;
import com.groweasy.paymentservice.resource.StripeTokenResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class StripePaymentMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public StripeTokenResource toResource(StripeTokenDto model){
        return mapper.map(model, StripeTokenResource.class);
    }

    public StripeTokenDto toModel(CreateStripeTokenResource resource){
        return mapper.map(resource, StripeTokenDto.class);
    }
}
