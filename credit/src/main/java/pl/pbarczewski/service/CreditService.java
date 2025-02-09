package pl.pbarczewski.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.util.RequestInterface;
import pl.pbarczewski.domain.CreditServiceInterface;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.infrastructure.model.Customer;
import pl.pbarczewski.infrastructure.model.Product;
import pl.pbarczewski.infrastructure.repository.CreditJpaRepository;
import pl.pbarczewski.request.GetRequest;
import pl.pbarczewski.request.PostRequest;
import pl.pbarczewski.request.Request;
import pl.pbarczewski.urls.QueryParameter;
import pl.pbarczewski.urls.Url;

@Service
public class CreditService implements CreditServiceInterface {
	private CreditRepositoryInterface creditRepositoryInterface;
	
	@Autowired
	public CreditService(CreditJpaRepository creditJpaRepository) {
		this.creditJpaRepository = creditJpaRepository;
	}

	public List<Credit> getCredits() {
		return creditJpaRepository.findAll();
	}

	@Override
	public String generateNumber() {
		String generateNumber = creditJpaRepository.gene
		return creditJpaRepository.getCreditByCreditNumber();
	}


	public int createCredit() throws Exception {
		getEntities(entities);
		post(Url.CUSTOMER_URL, customer);
		post(Url.PRODUCT_URL, product);
		completeCredit(customer, product);
		creditJpaRepository.save(this.entities.getCredit());
		return credit.getId();
	}


	private void post(Url url, EntityInterface entity) {
		if(entity != null) {
		request = new PostRequest<Object>(url, entity);
		RequestInterface.createConnection(request);
		} else {
			throw new NullPointerException("Entity is empty");
		}
	}
	
	// Metoda służąca do stworzenia połączenie typu "Get".
	// Przyjmuje argument typu "String".
	// Wykorzystuje interfejs "RequestInterface" do stworzenia połączenia.
	// Zwraca listę obiektów klasy "Product".
	@SuppressWarnings("unchecked")
	public List<Product> getProducts(String id) {
		request = new GetRequest(Url.PRODUCT_URL, product, QueryParameter.ID, id);
		RequestInterface.createConnection(request);
		return request.getListOfEntities();
		
	}
	
	// Metoda służąca do stworzenia połączenie typu "Get".
	// Przyjmuje argument typu "String".
	// Wykorzystuje interfejs "RequestInterface" do stworzenia połączenia.
	// Zwraca listę obiektów klasy "Customer".
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers(String id) {
		request = new GetRequest(Url.CUSTOMER_URL, customer, QueryParameter.ID, id);
		RequestInterface.createConnection(request);
		return request.getListOfEntities();
	}
	
	// Prywatna metoda pomocnicza, służy do wywołana metody "completeCredit". 
	// Przyjmuje dwa parametry, obiekt klasy "Customer" i obiekt klasy "Product".
	// Informuje o możliwości wyrzucenia wyjątku "Exception".
	private void completeCredit(Customer customer, Product product) throws Exception {
		this.credit.completeCredit(customer, product);
	}
}
