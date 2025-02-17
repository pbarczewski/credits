package pl.pbarczewski.domain;

import org.springframework.http.HttpStatus;

import java.util.concurrent.CompletableFuture;

public interface KafkaProducerServiceInterface {
    CompletableFuture<HttpStatus> sendCreditNumberToCustomer(String generatedNumber);
    CompletableFuture<HttpStatus> sendCreditNumberToProducer(String generatedNumber);
}
