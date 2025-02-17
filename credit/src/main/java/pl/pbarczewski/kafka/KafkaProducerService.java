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
import pl.pbarczewski.domain.model.Model;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@EnableKafka
public class KafkaProducerService implements KafkaProducerServiceInterface {

    private final String creditToCustomerTopic = "credit-to-customer";
    private final String creditToProducerTopic = "credit-to-producer";

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public CompletableFuture<HttpStatus> sendModel(String generatedNumber) {

    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToCustomer(String generatedNumber) {
        log.info("Uruchomienie przsyłania do modułu customer");

        return kafkaTemplate.send(creditToCustomerTopic, generatedNumber)
                .handle((result, ex) -> {
                    if (ex == null) {
                        log.info("Przesłąnie do customer udane");

                        //todo Do zrobienia
                        return HttpStatus.OK;

                    } else {
                        return HttpStatus.NOT_FOUND;
                    }
                });
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToProducer(String generatedNumber) {
        return null;
    }
}

