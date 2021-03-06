package pl.pbarczewski.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.entity.Customer;
import pl.pbarczewski.service.CustomerService;

// Kontroler modułu "customer", wykorzystuje pomocniczą klasę "CustomerService"
// Zawiera metodę "createCustomer umożliwiającą zapisywanie obiektu typu "Customer" do bazy danych
// Zawiera metodę "getCustomers" zwracającą listę obiektów typu "Customer"
@RestController
public class CustomerController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customers")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(@RequestParam (required= false) String id) {
		return customerService.getCustomersByIds(id);
	}
}
