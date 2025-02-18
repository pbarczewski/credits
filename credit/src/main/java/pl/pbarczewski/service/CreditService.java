package pl.pbarczewski.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.domain.model.CreditViewModel;
import pl.pbarczewski.rest.request.CreditRequest;

@Service
public class CreditService implements CreditServiceInterface {
	private CreditRepositoryInterface creditRepositoryInterface;

	Autowired
	public CreditService(CreditRepositoryInterface creditRepositoryInterface) {
		this.creditRepositoryInterface = creditRepositoryInterface;
	}

	@Override
	public List<CreditViewModel> getCreditCompleteInfo() {
		return null;
	}

	@Override
	public String createCredit(CreditRequest creditRequest) {
		return null;
	}

	@Override
	public CreditViewModel findSingleCredit(String creditName) {
		return null;
	}

	@Override
	public CreditRequest generateCreditNumber(CreditRequest creditRequest) {
		String creditNumber = creditRepositoryInterface.getCreatedCreditNumber();
		creditRequest.setCreditNumber(creditNumber);
		return creditRequest;
	}


}
