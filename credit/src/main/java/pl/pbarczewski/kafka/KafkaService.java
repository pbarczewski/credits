package pl.pbarczewski.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.pbarczewski.common.ValidationObject;
import pl.pbarczewski.domain.KafkaConsumerServiceInterface;
import pl.pbarczewski.domain.KafkaProducerServiceInterface;
import pl.pbarczewski.rest.request.CreditRequest;
import pl.pbarczewski.rest.response.CreditResponse;
import pl.pbarczewski.util.validator.CreditValidator;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaService implements KafkaProducerServiceInterface, KafkaConsumerServiceInterface {

    private final String creditToCustomerTopic = "credit-to-customer";
    private final String creditToProducerTopic = "credit-to-producer";
    private final KafkaTemplate<String, CreditRequest> kafkaTemplate;
    private CreditValidator creditValidator;

    @Autowired
    public KafkaService(CreditValidator creditValidator, KafkaTemplate<String, CreditRequest> kafkaTemplate) {
        this.creditValidator = creditValidator;
        this.kafkaTemplate = kafkaTemplate;
    }


    private CreditResponse creditResponse(CreditRequest creditRequest) {
        ValidationObject validationObject = creditValidator.validate(creditRequest.getCreditModel());
        CreditResponse creditResponse = new CreditResponse();
        return sendCreditNumberToCustomer(creditRequest)
                .thenApply(status -> {
                    if (status == HttpStatus.OK) {
                        return CreditResponse.builder()
                                .msg(Arrays.asList("Kredyt został stworzony pomyślnie"))
                                .httpStatus(HttpStatus.OK)
                                .build();
                    } else {
                        return CreditResponse.builder()
                                .msg(Arrays.asList("Nie udało się stworzyć kredytu"))
                                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                .build();
                    }
                })
                .exceptionally(ex -> CreditResponse.builder()
                        .msg(Arrays.asList("Wystąpił błąd podczas przetwarzania kredytu"))
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build())
                .join();
    }

    @Override
    @KafkaListener(topics = "customer-response", groupId = "credit-service-group")
    public CreditResponse getCustomerResponse(CreditResponse creditResponse) {
        if(creditResponse.getHttpStatus() == HttpStatus.NOT_FOUND
                || creditResponse.getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.info("Zasób credit response nie został utworzony");
            return null;
        }
        log.info(("Zasób Customer został utworzony dla numeru kredytu: " + creditResponse.getCreatedObjectUuid()));

        return creditResponse;
    }

    @Override
    public CreditResponse getProducerResponse(CreditResponse creditResponse) {
        return null;
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToCustomer(CreditRequest creditRequest) {
        return kafkaTemplate.send(creditToCustomerTopic, creditRequest)
                .thenApply(result -> {
                    log.info("Przesłanie do customer udane");
                    return HttpStatus.OK;
                })
                .exceptionally(ex -> {
                    log.error("Błąd przsyłania do customer", ex);
                    return HttpStatus.INTERNAL_SERVER_ERROR;
                });
    }

    @Override
    public CompletableFuture<HttpStatus> sendCreditNumberToProducer(CreditRequest creditRequest) {
        return null;
    }
}
