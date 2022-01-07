package pl.pbarczewski.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.pbarczewski.entity.Customer;

@Testcontainers
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	private Customer firstCustomer;
	private Customer secondCustomer;
	private Customer thirdCustomer;
	private HttpEntity<Object> entity;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@SuppressWarnings("rawtypes")
	@Container
	private static MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest");
	
	@DynamicPropertySource
	public static void ovverideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
	}
	
	@BeforeEach
	void init() {
		firstCustomer = new Customer("Jan", "Kowalski", "12345678910");
		firstCustomer.setCreditId(12345);
		secondCustomer =  new Customer("Janina", "Kowalska", "12345678911");
		secondCustomer.setCreditId(54321);
		thirdCustomer = new Customer("Robert", "Lewandowski", "12345678912");
		thirdCustomer.setCreditId(98765);
		entity = new HttpEntity<Object>(new HttpHeaders());
	}
	
	@Test
	public void httpStatus_should_return_OK_if_url_is_correct() {
		//given
		//when
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:"+port +"/customers?id=", HttpMethod.GET, entity , String.class);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void httpStatus_should_return_INTERNAL_SERVER_ERROR_if_url_is_without_parameters() {
		//given
		//when
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:"+port +"/customers?", HttpMethod.GET, entity , String.class);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	void httpStatus_should_return_NOT_FOUND_if_url_is_incorrect() {
		//given
		//when
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:"+port +"/customer", HttpMethod.GET, entity , String.class);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	void httpStatus_should_return_METHOD_NOT_ALLOWED_if_http_method_is_PUT() {
		//given
		//when
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:"+port +"/customers", HttpMethod.PUT, entity , String.class);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@Test
	void httpStatus_should_return_METHOD_NOT_ALLOWED_if_http_method_is_DELETE() {
		//given
		//when
		ResponseEntity<String> response = this.restTemplate.exchange("http://localhost:"+port +"/customers", HttpMethod.DELETE, entity , String.class);
		//then
		assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@Test
	void customer_list_should_be_empty_if_no_customer_is_added() {
		assertThat(this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=", Customer[].class)).isEmpty();
	}
	
	@Test
	void customer_list_should_not_be_empty_if_product_is_added() {
		//given
		//when
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", firstCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", secondCustomer, Customer.class);
		Customer[] customers = this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=", Customer[].class);
		//then
		assertEquals(customers.length, 2);
	}
	
	@Test
	void check_if_customer_is_added_to_the_list_correctly() {
		//given
		//when
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", firstCustomer, Customer.class);
		Customer[] customers = this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=", Customer[].class);
		Customer receivedCustomer = customers[0];
		//then
		assertAll(
					() -> assertEquals(receivedCustomer.getCreditId(), 12345),
					() -> assertEquals(receivedCustomer.getFirstName(), "Jan"),
					() -> assertEquals(receivedCustomer.getSurname(), "Kowalski"),
					() -> assertEquals(receivedCustomer.getPesel(), "12345678910")
				);
	}
	
	@Test
	void only_customers_with_provided_id_should_be_returned() {
		//given
		//when
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", firstCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", secondCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", thirdCustomer, Customer.class);
		Customer[] customers = this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=12345,98765", Customer[].class);
		//then
		assertAll(
				() -> assertEquals(customers.length, 2),
				() -> assertEquals(customers[0].getFirstName(), "Jan"),
				() -> assertEquals(customers[0].getCreditId(), 12345),
				() -> assertEquals(customers[1].getSurname(), "Lewandowski"),
				() -> assertEquals(customers[1].getCreditId(), 98765)
				);
	}
	
	@Test
	void only_customers_with_correct_parameters_should_be_returned() {
		//given
		//when
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", firstCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", secondCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/customers", thirdCustomer, Customer.class);
		Customer[] customers = this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=12345,xxxx,54321", Customer[].class);
		
		//then
		assertAll(
				() -> assertEquals(customers.length, 2),
				() -> assertEquals(customers[0].getFirstName(), "Jan"),
				() -> assertEquals(customers[0].getCreditId(), 12345),
				() -> assertEquals(customers[1].getFirstName(), "Janina"),
				() -> assertEquals(customers[1].getCreditId(), 54321)
				);
	}
	
	@Test
	void no_customers_should_be_returned_if_parameters_are_wrong() {
		//given
		//when
		this.restTemplate.postForObject("http://localhost:"+port +"/products", firstCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/products", secondCustomer, Customer.class);
		this.restTemplate.postForObject("http://localhost:"+port +"/products", thirdCustomer, Customer.class);
		//then
		assertThat(this.restTemplate.getForObject("http://localhost:"+port +"/customers?id=1111,xxxx,123dadsa", Customer[].class)).isEmpty();
	}
}
