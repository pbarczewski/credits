package pl.pbarczewski.domain;

import pl.pbarczewski.rest.response.CreditResponse;

public interface KafkaConsumerServiceInterface {
    CreditResponse getCustomerResponse(CreditResponse creditResponse);

    CreditResponse getProducerResponse(CreditResponse creditResponse);

}
