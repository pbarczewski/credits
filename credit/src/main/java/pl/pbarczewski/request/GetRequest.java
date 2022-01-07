package pl.pbarczewski.request;
import java.util.Arrays;
import org.springframework.http.HttpMethod;
import pl.pbarczewski.entity.Customer;
import pl.pbarczewski.entity.EntityInterface;
import pl.pbarczewski.entity.Product;
import pl.pbarczewski.urls.QueryParameter;
import pl.pbarczewski.urls.Url;

//Klasa dziedzicząca po abstrakcyjnej klasie "Request".
//Służy do tworzenia zapytań typu "Get".
public class GetRequest extends Request<Object> {
	private String parameterValues;
	private QueryParameter queryParameter;

	public GetRequest(Url url, EntityInterface entityInterface, QueryParameter queryParameter,
			String parametersValue) {
		super(url, entityInterface);
		this.httpMethod = HttpMethod.GET;
		this.queryParameter = queryParameter;
		this.parameterValues = parametersValue;
	}
	
	// Prywatna metoda pomocnicza.
	// Tworzy adres url wraz z podanym parametrem.
	// Wykorzystuje switch do stworzenia poprawnego połączenia i zwrócenia 
	// listy obiektów odpowiedniego typu.
	// Rzuca wyjątek "IllegalArgumentException".
	private void getEntities(EntityInterface entityInterface) {
		String url = this.url.getUrl() + queryParameter.getIds();
		  switch(entityInterface.getClass().getSimpleName()) { 
		  case "Product":
			  this.listOfEntities = Arrays.asList(restTemplate.getForObject(url, Product[].class, parameterValues)); 
			  break;
		  case "Customer":
			  this.listOfEntities = Arrays.asList(restTemplate.getForObject(url, Customer[].class, parameterValues)); 	  
			  break;
		  default:
			  throw new IllegalArgumentException("Wrong entity");	  
		  }
	}
	
	// Nadpisana metoda klasy "Request".
	// Wywołuje prywatną metodę "getEntities".
	// w celu zapełnienia listy "listOfEntities" obiektami odpowiedniego typu.
	@Override
	public void execute() {
		getEntities(entityInterface);
	}
}
