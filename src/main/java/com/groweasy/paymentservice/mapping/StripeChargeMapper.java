package com.groweasy.paymentservice.mapping;


import com.groweasy.paymentservice.domain.model.StripeChargeDto;
import com.groweasy.paymentservice.resource.CreateStripeChargeResource;
import com.groweasy.paymentservice.resource.StripeChargeResource;
import org.springframework.beans.factory.annotation.Autowired;

public class StripeChargeMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public StripeChargeResource toResource(StripeChargeDto model){
        return mapper.map(model, StripeChargeResource.class);
    }

    public StripeChargeDto toModel(CreateStripeChargeResource resource){
        return mapper.map(resource, StripeChargeDto.class);
    }
}
