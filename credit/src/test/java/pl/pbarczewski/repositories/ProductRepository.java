package pl.pbarczewski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pbarczewski.infrastructure.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
