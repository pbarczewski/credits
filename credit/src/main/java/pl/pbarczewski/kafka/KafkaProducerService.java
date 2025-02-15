package pl.pbarczewski.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.model.Model;

@Service
@EnableKafka
public class KafkaProducerService {

    private final String topic = "credit-topic";

    private KafkaTemplate<String, Model> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Model> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendModel(Model model) {
        kafkaTemplate.send(topic, model);
    }

}
