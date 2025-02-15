package pl.pbarczewski.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import pl.pbarczewski.common.Connection;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.rest.response.ResponseBody;
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

}
