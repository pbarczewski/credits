package pl.pbarczewski.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.domain.CustomerServiceInterface;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.infrastructure.model.Customer;
import pl.pbarczewski.service.CustomerService;


@RestController("customer")
public class CustomerController {
	private CustomerServiceInterface customerServiceInterface;
	
	@Autowired
	public CustomerController(CustomerServiceInterface customerServiceInterface) {
		this.customerServiceInterface = customerServiceInterface;
	}

	@PostMapping("/")
	public void createCustomer(@RequestBody CustomerModel customerModel, String creditNumber) {
		customerServiceInterface.saveCustomer(customer);
	}
	

}
