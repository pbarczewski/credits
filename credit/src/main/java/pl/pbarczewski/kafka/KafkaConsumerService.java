package pl.pbarczewski.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "customer-topic", groupId = "credit-consumer-group")
    public void listenCustomerCreated(String customerName) {
        System.out.println("Received new customer: " + customerName);
    }
}


