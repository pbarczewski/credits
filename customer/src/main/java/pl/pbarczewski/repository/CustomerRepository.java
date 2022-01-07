package pl.pbarczewski.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.entity.Customer;

// Repozytorium modułu "customer" wykorzystujące metody interfejsu JpaRepository
// do manipulowania obiektami typu "Customer" w bazie danych 
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
