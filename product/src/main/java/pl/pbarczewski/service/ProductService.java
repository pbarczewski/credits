package pl.pbarczewski.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pbarczewski.entity.Product;
import pl.pbarczewski.repository.ProductRepository;

// Główny serwis modułu "product"
// Zawiera metody i zmienne służace do komunikacji z bazą danych, zapisywania i odbierania obiektów.
@Service
public class ProductService {
	private ProductRepository productRepository;
	private List<Integer> listOfParameters;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.listOfParameters = null;
		this.productRepository = productRepository;
		this.listOfParameters = new ArrayList<>();
	}
	
	public ProductService() {
		
	}

	// Metoda służaca do zwrócenia listy z obiektami typu "Product"
	// Przyjmuje argument typu "String"
	// Wykorzystuje pomocniczą metodę "getIds"
	// Zwraca listę obiektów typu "Product"
	public List<Product> getProductsByIds(String id) {
		try {
			getIds(id);
			if(listOfParameters.size() > 0) {
				return productRepository.findAllById(listOfParameters);
			} else {
				return productRepository.findAll();
			} 
		}
		finally {
			listOfParameters.clear();
		}
	}
	
	// Metoda służąca do zapisu obiektu typu "Product" do bazy danych.
	public void saveProduct(Product product) {
		productRepository.save(product);
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
