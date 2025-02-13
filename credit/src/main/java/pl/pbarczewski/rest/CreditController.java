package pl.pbarczewski.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.service.CreditService;

@RestController("credit")
public class CreditController {
	private CreditService creditService;

	@Autowired
	public CreditController(CreditService creditService) {
		this.creditService = creditService;
	}

	@GetMapping("/list")
	public List<Credit> getCredits() {
		return creditService.getCredits();
	}
	
	/*@PostMapping("/")
	public int createCredit(@Valid @RequestBody Entities entities) throws Exception {
		return creditService.createCredit(entities);
	}

	@GetMapping("/products")
	public List<Product> getProducts(@RequestParam (required= false) String id) {
		return creditService.getProducts(id);
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(@RequestParam (required= false) String id) {
		return creditService.getCustomers(id);
	}*/
}
