package pl.pbarczewski.request;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import pl.pbarczewski.entity.EntityInterface;
import pl.pbarczewski.urls.Url;

// Abstrakcyjna klasa służąca jako baza do konstruowania zapytań http.
// Zawiera metodę abstrakcyjną "execute" typu "void".
// Zawiera metodę zwracają listę obiektów typu "EntityInterface".
public abstract class Request<T> {
	protected final RestTemplate restTemplate = new RestTemplate();
	protected final Url url;
	protected final EntityInterface entityInterface;
	protected HttpMethod httpMethod;
	protected List<EntityInterface> listOfEntities;

	public Request(Url url, EntityInterface entityInterface) {
		this.url = url;
		this.entityInterface = entityInterface;
	}
	
	public abstract void execute();

	public List<EntityInterface> getListOfEntities() {
		return listOfEntities;
	}
}
