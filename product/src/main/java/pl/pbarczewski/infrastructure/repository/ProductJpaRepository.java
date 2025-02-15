package pl.pbarczewski.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.infrastructure.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
	
}
