package pl.pbarczewski.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pbarczewski.infrastructure.model.Product;
import pl.pbarczewski.service.ProductService;

@RestController("product")
public class ProductController {
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/")
	public void createProduct(@RequestBody Product product) {
		productService.saveProduct(product);
	}
	
	@GetMapping("/list")
	public List<Product> getProducts(@RequestParam (required= false) String id) { 
		return productService.getProductsByIds(id); 
	}
}
