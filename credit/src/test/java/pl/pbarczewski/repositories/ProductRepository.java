package pl.pbarczewski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pbarczewski.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
