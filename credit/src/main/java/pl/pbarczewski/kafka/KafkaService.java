package pl.pbarczewski.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import pl.pbarczewski.domain.KafkaConsumerServiceInterface;
import pl.pbarczewski.domain.KafkaProducerServiceInterface;
import java.util.concurrent.CompletableFuture;

public class KafkaService implements KafkaConsumerServiceInterface, KafkaProducerServiceInterface {

    private KafkaConsumerServiceInterface kafkaConsumerServiceInterface;
    private KafkaProducerServiceInterface kafkaProducerServiceInterface;


    @Autowired
    public KafkaService(KafkaConsumerServiceInterface kafkaConsumerServiceInterface, KafkaProducerServiceInterface kafkaProducerServiceInterface) {
        this.kafkaConsumerServiceInterface = kafkaConsumerServiceInterface;
        this.kafkaProducerServiceInterface = kafkaProducerServiceInterface;
    }


    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToCustomer(String generatedNumber) {
        return kafkaProducerServiceInterface.sendCreditNumberToCustomer(generatedNumber);
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToProducer(String generatedNumber) {
        return kafkaProducerServiceInterface.sendCreditNumberToProducer(generatedNumber);
    }
}
