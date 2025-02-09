package pl.pbarczewski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pbarczewski.infrastructure.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
