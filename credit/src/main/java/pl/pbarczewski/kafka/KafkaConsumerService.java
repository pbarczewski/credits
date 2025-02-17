package pl.pbarczewski.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.KafkaConsumerServiceInterface;

@Component
public class KafkaConsumerService implements KafkaConsumerServiceInterface {



    @KafkaListener(topics = "customer-topic", groupId = "credit-consumer-group")
    public void listenCustomerCreated(String customerName) {
        System.out.println("Received new customer: " + customerName);
    }
}


