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
import pl.pbarczewski.infrastructure.model.Product;
import pl.pbarczewski.service.ProductService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Testcontainers
public class ProductDataBaseTest {
	
	private Product firstProduct;
	private Product secondProduct;
	private Product thirdProduct;
	
	@Autowired
	private ProductService productService;
	List<Product> products = new ArrayList<>();
	private Validator validator;
	
	@BeforeEach
	void init() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
        firstProduct = new Product("First Product", 100);
        firstProduct.setCreditId(12345);
        secondProduct = new Product("Second Product", 200);
        secondProduct.setCreditId(123456);	
        thirdProduct = new Product("New Product3", 300);
        thirdProduct.setCreditId(45678);
	}
	
	@SuppressWarnings("rawtypes")
	@Container
	private static MySQLContainer mysqlContainer = new MySQLContainer("mysql:latest");
	
	@DynamicPropertySource
	public static void ovverideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mysqlContainer::getUsername);
		registry.add("spring.datasource.password", mysqlContainer::getPassword);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {""})
	void all_products_should_be_returned_if_id_is_Empty(String id) {
		//given
		//when
		productService.saveProduct(firstProduct);
		productService.saveProduct(secondProduct);
		productService.saveProduct(thirdProduct);
		List<Product> products = productService.getProductsByIds(id);
		//then
		assertThat(products).hasSize(3);
	}
	
	@Test
	void check_if_product_is_saved_in_database() {
		//given
		Product product = new Product("New Product", 100);
		product.setCreditId(4555);
		//when
		products = productService.getProductsByIds("");
		assertThat(products).hasSize(0);
		//then
		productService.saveProduct(product);
		products = productService.getProductsByIds("4555");
		assertThat(products).hasSize(1);
		assertEquals(products.get(0).getCreditId(), 4555);
	}
	
	@Test
	void product_with_correct_parameters_should_pass_validation() {
		//given
		//when
	    Product product = new Product("New", 100);
	    Set<ConstraintViolation<Product>> violations = this.validator.validate(product);
	    //then
	    assertTrue(violations.isEmpty());
	}  
	
	@Test
	void product_with_incorrect_parameters_should__not_pass_validation() {
		//given
		//when
        Product product = new Product("09", 0);
        Set<ConstraintViolation<Product>> violations = this.validator.validate(product);
        //then
        assertEquals(violations.size(), 2);
	}  
	
	@Test
	void check_if_saved_product_contains_correct_values() {
		//given
		Product receivedProduct;
		Product product = new Product("New Product", 100);
		product.setCreditId(4555);
		//when
		productService.saveProduct(product);
		products = productService.getProductsByIds("4555");
		receivedProduct = products.get(0);
		//then
		assertAll(
				() -> assertEquals("New Product", receivedProduct.getName()),
				() -> assertEquals(100, receivedProduct.getValue()),
				() -> assertEquals(4555, receivedProduct.getCreditId())
				);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"45678,12345"})
	void check_If_Database_Returns_Producst_With_Provided_Id(String id) {
		//given
		//when
		productService.saveProduct(firstProduct);
		productService.saveProduct(secondProduct);
		productService.saveProduct(thirdProduct);
		products = productService.getProductsByIds(id);
		//then
		assertThat(products).hasSize(2);
		assertThat(products.get(0)).isNotNull();
		assertEquals(products.get(1).getValue(), 300);
	}
}
