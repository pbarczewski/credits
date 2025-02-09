package pl.pbarczewski.infrastructure.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.infrastructure.model.Credit;

// Repozytorium modułu "credit" wykorzystujące metody interfejsu JpaRepository
// do manipulowania obiektami typu "Credit" w bazie danych. 
@Repository
public interface CreditJpaRepository extends JpaRepository<Credit, Integer> {


    @Query("Select c FROM Credit c WHERE c.creditNumber = :creditNumber")
    Credit getCreditByCreditNumber(String creditNumber);

}
