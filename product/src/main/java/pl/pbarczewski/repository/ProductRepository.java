package pl.pbarczewski.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.entity.Product;

// Repozytorium modułu "product" wykorzystujące metody interfejsu JpaRepository
// do manipulowania obiektami typu "Product" w bazie danych.
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
