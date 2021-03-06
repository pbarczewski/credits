package pl.pbarczewski.wrapper;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import pl.pbarczewski.components.IdGenerator;
import pl.pbarczewski.entity.Credit;
import pl.pbarczewski.entity.Customer;
import pl.pbarczewski.entity.EntityInterface;
import pl.pbarczewski.entity.Product;

// Klasa służaca do stworzenia i przechowania obiektów typu "Credit", "Product" i "Customer".
// Posiada metodę "setId", oraz gettery i settery dla poszczególnych pól.
public class Entities {
	@Valid
	private Credit credit;
	@Valid
	private Product product;
	@Valid
	private Customer customer;
	private final List<EntityInterface> entities = new ArrayList<>();
	
	public Entities(@Valid Credit credit, @Valid Product product, @Valid Customer customer) {
		this.credit = credit;
		this.product = product;
		this.customer = customer;
	}

	// Metoda służąca nadaniu wszystkim encją jednakowy numer id.
	// Rzuca wyjątek Excepton. 
	// Niczego nie zwraca.
	public void setId() throws Exception {
		entities.add(this.credit);
		entities.add(this.customer);
		entities.add(this.product);
		if(entities.size() < 3) {
			throw new Exception("No all entities have been created correctly");
		} else {
			int id = IdGenerator.generateId();
			for(EntityInterface singleEntity : entities) {
				singleEntity.setCreditId(id);
			}
		}
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
