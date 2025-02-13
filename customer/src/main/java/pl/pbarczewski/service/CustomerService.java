package pl.pbarczewski.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pbarczewski.domain.CustomerRepositoryInterface;
import pl.pbarczewski.domain.CustomerServiceInterface;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.infrastructure.model.Customer;
import pl.pbarczewski.infrastructure.repository.CustomerJpaRepository;

@Service
public class CustomerService implements CustomerServiceInterface {
	private CustomerRepositoryInterface customerRepositoryInterface;
	
	@Autowired
	public CustomerService(CustomerRepositoryInterface customerRepositoryInterface) {
		this.customerRepositoryInterface = customerRepositoryInterface;
	}

	@Override
	public List<CustomerModel> getCredits() {
		return null;
	}

	@Override
	public String saveCustomer(String creditNumber, CustomerModel customerModel) {
		return customerRepositoryInterface.createCredit();
	}

}

