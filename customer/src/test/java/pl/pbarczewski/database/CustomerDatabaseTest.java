package pl.pbarczewski.database;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.pbarczewski.infrastructure.model.Customer;
import pl.pbarczewski.service.CustomerService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Testcontainers
class CustomerDatabaseTest {
	
	private Customer firstCustomer;
	private Customer secondCustomer;
	private Customer thirdCustomer;

	@Autowired
	private CustomerService customerService;
	List<Customer> customers = new ArrayList<>();
	private Validator validator;
	
	@SuppressWarnings("rawtypes")
	@Container
	private static MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest");
	
	@BeforeEach
	void init() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
        firstCustomer = new Customer("Adam", "Kowalski", "12345678901");
        firstCustomer.setCreditId(123);
        secondCustomer = new Customer("Jan", "Nowak", "98765432112");
        secondCustomer.setCreditId(234);
        thirdCustomer = new Customer("Robert", "Lewandowski", "11122233345");
        thirdCustomer.setCreditId(45678);
	}
	
	@DynamicPropertySource
	public static void ovverideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
	}
	
	@Test
	void customer_with_correct_parameters_should_pass_validation() {
		//given
		Customer customer = new Customer("Jan", "Kowalski", "12345678910");
		//when
        Set<ConstraintViolation<Customer>> violations = this.validator.validate(customer);
        //then
	    assertTrue(violations.isEmpty());
	}  
	
	@Test
	void customer_with_incorrect_parameters_should__not_pass_validation() {
		//given
        Customer customer = new Customer("jAN", "_Kowalski", "1234567891091");
        //when
        Set<ConstraintViolation<Customer>> violations = this.validator.validate(customer);
        //then
        assertEquals(violations.size(), 3);
	}  
	
	@ParameterizedTest
	@ValueSource(strings = {""})
	void all_customers_should_be_returned_if_id_is_Empty(String id) {
		//given
		//when
		customerService.saveCustomer(firstCustomer);
		customerService.saveCustomer(secondCustomer);
		customerService.saveCustomer(thirdCustomer);
		customers = customerService.getCustomersByIds(id);
		//then
		assertThat(customers).hasSize(3);
	}
	
	@Test
	void check_if_customer_is_saved_in_database() {
		//given
		Customer customer = new Customer("Adam", "Kowalski", "12345678901");
		customer.setCreditId(1111);
		//when
		customers = customerService.getCustomersByIds("");
		assertThat(customers).hasSize(0);
		//then
		customerService.saveCustomer(customer);;
		customers = customerService.getCustomersByIds("1111");
		assertThat(customers).hasSize(1);
		assertEquals(customers.get(0).getCreditId(), 1111);
	}
	
	@Test
	void check_if_saved_customer_contains_correct_values() {
		//given
		Customer receivedCustomer;
		Customer customer = new Customer("Adam", "Kowalski", "12345678901");
		customer.setCreditId(1111);
		//when
		customerService.saveCustomer(customer);
		customers = customerService.getCustomersByIds("1111");
		receivedCustomer = customers.get(0);
		//then
		assertAll(
				() -> assertEquals("Adam", receivedCustomer.getFirstName()),
				() -> assertEquals("Kowalski", receivedCustomer.getSurname()),
				() -> assertEquals("12345678901", receivedCustomer.getPesel()),
				() -> assertEquals(1111, receivedCustomer.getCreditId())
				);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"123,234"})
	void only_customers_with_provided_id_should_be_returned(String id) {
		//given
		//when
		customerService.saveCustomer(firstCustomer);
		customerService.saveCustomer(secondCustomer);
		customerService.saveCustomer(thirdCustomer);
		customers = customerService.getCustomersByIds(id);
		//then
		assertThat(customers).hasSize(2);
		assertThat(customers.get(0)).isNotNull();
		assertEquals(customers.get(1).getCreditId(), 234);
		assertEquals(customers.get(0).getPesel(), "12345678901");
	}
}
