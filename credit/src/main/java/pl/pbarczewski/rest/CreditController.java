package pl.pbarczewski.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.domain.CreditServiceInterface;


@RestController("credit")
public class CreditController {
	private CreditServiceInterface creditServiceInterface;

	@Autowired
	public CreditController(CreditServiceInterface creditServiceInterface) {
		this.creditServiceInterface = creditServiceInterface;
	}

}
