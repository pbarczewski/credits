package pl.pbarczewski.domain;

import org.springframework.http.HttpStatus;
import pl.pbarczewski.rest.request.CreditRequest;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducerServiceInterface {
    CompletableFuture<HttpStatus> sendCreditNumberToCustomer(CreditRequest creditRequest);
    CompletableFuture<HttpStatus> sendCreditNumberToProducer(CreditRequest creditRequest);
}
