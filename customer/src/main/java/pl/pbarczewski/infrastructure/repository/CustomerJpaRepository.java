package pl.pbarczewski.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.infrastructure.model.Customer;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {

}
