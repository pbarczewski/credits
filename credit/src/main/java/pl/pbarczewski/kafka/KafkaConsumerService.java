package pl.pbarczewski.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.KafkaConsumerServiceInterface;
import pl.pbarczewski.rest.response.CreditResponse;

@Slf4j
@Component
public class KafkaConsumerService implements KafkaConsumerServiceInterface {
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaConsumerService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }


}


