package pl.pbarczewski.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.entity.Credit;
import pl.pbarczewski.entity.Customer;
import pl.pbarczewski.entity.Product;
import pl.pbarczewski.service.CreditService;
import pl.pbarczewski.wrapper.Entities;

// Głowny kontroler aplikacji.
// Korzysta z klasy 'CreditService' która stanowi główny serwis aplikacji.
// Kontroler zawiera metody pozwalające na przesyłanie i odbieranie danych za pomocą zapytań Http.
// Metoda "createCredit" wywołuje metodę klasy "CreditService" o tej samej nazwie i
// zwraca wartość typu "int".
// Metoda "getCredits" wywołuje metodę klasy "CreditService" o tej samej nazwie i zwraca
// listę obiektów typu "Credit".
// Metody "getProducts?" i "getCustomers" wywołują metody klasy "CreditService" o tych samych nazwach
// i zwracają listy z obiektami odpowiednich typów
// przyjmują parametr typu "String" (nie jest wymagany).
@RestController
public class CreditController {
	private CreditService creditService;

	@Autowired
	public CreditController(CreditService creditService) {
		this.creditService = creditService;
	}

	@GetMapping("/credits")
	public List<Credit> getCredits() {
		return creditService.getCredits();
	}
	
	@PostMapping("/credits")
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
	}
}
