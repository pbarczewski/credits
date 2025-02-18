package pl.pbarczewski.rest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.rest.request.CreditRequest;
import pl.pbarczewski.rest.response.CreditResponse;


@Slf4j
@RestController("credit")
public class CreditController {
	private CreditServiceInterface creditServiceInterface;



	@Autowired
	public CreditController(CreditServiceInterface creditServiceInterface) {
		this.creditServiceInterface = creditServiceInterface;
	}

	@PostMapping("/")
	public ResponseEntity<CreditResponse> createCredit(CreditRequest creditRequest) {
		if(creditRequest == null) {
			return null;
		}
		creditRequest = creditServiceInterface.generateCreditNumber(creditRequest);

		log.info("Generated credit number: {}" + creditRequest.getCreditNumber());


	}

}
