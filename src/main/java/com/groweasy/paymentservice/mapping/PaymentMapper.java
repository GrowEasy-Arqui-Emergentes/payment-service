package com.groweasy.paymentservice.mapping;


import com.groweasy.paymentservice.domain.model.Payment;
import com.groweasy.paymentservice.resource.CreatePaymentResource;
import com.groweasy.paymentservice.resource.PaymentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PaymentMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;
    public PaymentResource toResource(Payment model){
        return mapper.map(model, PaymentResource.class);
    }
    public Payment toModel(CreatePaymentResource resource){
        return mapper.map(resource, Payment.class);
    }
    public Page<PaymentResource> modelListPage(List<Payment> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, PaymentResource.class), pageable, modelList.size());
    }

}
