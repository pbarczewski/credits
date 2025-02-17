package pl.pbarczewski.rest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.kafka.KafkaProducerService;


@Slf4j
@RestController("credit")
public class CreditController {
	private CreditServiceInterface creditServiceInterface;

	private KafkaProducerService kafkaProducerService;

	@Autowired
	public CreditController(CreditServiceInterface creditServiceInterface, KafkaProducerService kafkaProducerService) {
		this.creditServiceInterface = creditServiceInterface;
		this.kafkaProducerService = kafkaProducerService;
	}

	@PostMapping("/")
	public ResponseEntity<String> createCredit() {
		String creditNumber = creditServiceInterface.getCreatedCreditNumber();
		log.info("Generated credit number: {}" + creditNumber);
		HttpStatus httpStatus = kafkaProducerService.sendModel(creditNumber).thenApply(x -> x);

	}

}
