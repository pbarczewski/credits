package pl.pbarczewski.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.entity.Credit;

// Repozytorium modułu "credit" wykorzystujące metody interfejsu JpaRepository
// do manipulowania obiektami typu "Credit" w bazie danych. 
@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {

}
