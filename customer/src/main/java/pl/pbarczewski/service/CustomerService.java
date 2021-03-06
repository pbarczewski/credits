package pl.pbarczewski.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pbarczewski.entity.Customer;
import pl.pbarczewski.repository.CustomerRepository;

//Główny serwis modułu "customer"
//Zawiera metody i zmienne służace do komunikacji z bazą danych, zapisywania i odbierania obiektów.
@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	private final List<Integer> listOfParameters;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.listOfParameters = new ArrayList<>();
	}
	
	// Metoda służaca do zwrócenia listy z obiektami typu "Customer"
		// Przyjmuje argument typu "String"
		// Wykorzystuje pomocniczą metodę "getIds"
		// Zwraca listę obiektów typu "Customer"
	public List<Customer> getCustomersByIds(String id) {
		try {
		getIds(id);
			if(listOfParameters.size() > 0) {
				return customerRepository.findAllById(listOfParameters);
			} else {
			return customerRepository.findAll();
		} 
		} finally {
			this.listOfParameters.clear();
		}
	}

	// Metoda służąca do zapisu obiektu typu "Customer" do bazy danych.
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	// Metoda służąca do wyodrębnienia numerów "id" z przekazanego parametru.
	// Dzieli obiekt typu "string" i sprawdza czy podzielone elementy są liczbami całkowitymi
	// po czym dodaje je do listy "listOfParameters".
	// Metoda niczego nie zwraca.
	private void getIds(String id) {
		String[] splitted = id.split(",");
		for(String singleString : splitted) {
			if(singleString.matches("[0-9]+")) {
			listOfParameters.add(Integer.parseInt(singleString));
			} 
		}
	}
}
