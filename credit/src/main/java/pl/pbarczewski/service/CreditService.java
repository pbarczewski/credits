package pl.pbarczewski.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import pl.pbarczewski.common.Connection;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.rest.ResponseBody;
import pl.pbarczewski.rest.request.CreditRequest;
import pl.pbarczewski.util.validator.Url;

@Service
public class CreditService implements CreditServiceInterface {
	private CreditRepositoryInterface creditRepositoryInterface;

	@Autowired
	public CreditService(CreditRepositoryInterface creditRepositoryInterface) {
		this.creditRepositoryInterface = creditRepositoryInterface;
	}

	public List<CustomerModel> getCredits() {
		return creditRepositoryInterface.getCredits();
	}

	@Override
	public String createCredit(CreditRequest creditRequest) {
		String generatedCreditNumber = creditRepositoryInterface.generateNumber();
		creditRequest.getCreditModel().setCreditNumber(generatedCreditNumber);

		ResponseBody responseBody = connectToMicroSystems(Url.PRODUCT_URL, creditRequest);

		if(responseBody.getHttpStatus() != HttpStatus.OK) {
			return null;
		}

		return null;
	}




	private ResponseBody connectToMicroSystems(Url url, CreditRequest creditRequest) {
		HttpEntity<CreditRequest> httpEntity = new HttpEntity<>(creditRequest, Connection.generateHttpHeaders());
		try {
			ResponseEntity<ResponseBody> responseBody = Connection.createConnection(url, HttpMethod.POST, httpEntity);
			return responseBody.getBody();
		} catch (Exception e) {
			return null;
		}
	}

	/*private String connectToCustomerSystem(String generatedCreditNumber, CreditRequest creditRequest, HttpEntity<CreditRequest> httpEntity) {
		Connection.createConnection(Url.CUSTOMER_URL, HttpMethod.POST, httpEntity)
	}*/



}
