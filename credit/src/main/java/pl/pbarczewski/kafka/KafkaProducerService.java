package pl.pbarczewski.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import pl.pbarczewski.domain.KafkaProducerServiceInterface;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.Model;
import pl.pbarczewski.rest.request.CreditRequest;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@EnableKafka
public class KafkaProducerService implements KafkaProducerServiceInterface {

    private final String creditToCustomerTopic = "credit-to-customer";
    private final String creditToProducerTopic = "credit-to-producer";

    private final KafkaTemplate<String, CreditRequest> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, CreditRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToCustomer(CreditRequest creditRequest) {
        log.info("Uruchomienie przsyłania do modułu customer");
        return kafkaTemplate.send(creditToCustomerTopic, creditRequest)
                .thenApply(result -> HttpStatus.OK)
                .exceptionally(ex -> HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToProducer(CreditRequest creditRequest) {
        log.info("Uruchomienie przsyłania do modułu producer");
        return kafkaTemplate.send(creditToProducerTopic, creditRequest)
                .thenApply(result -> HttpStatus.OK)
                .exceptionally(ex -> HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

