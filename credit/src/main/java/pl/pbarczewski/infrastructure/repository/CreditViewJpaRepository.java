package pl.pbarczewski.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.infrastructure.model.CreditView;


@Repository
public interface CreditViewJpaRepository extends JpaRepository<CreditView, Integer> {
    @Query("Select c FROM CreditView c WHERE c.creditNumber = :creditNumber")
    CreditView getCreditByCreditNumber(String creditNumber);

    @Query("Select c FROM CreditView c WHERE c.productName = :productName")
    CreditView getCreditByProductName(String productName);

    @Query("Select c FROM CreditView c WHERE c.surname = :surname")
    CreditView getCreditByCustomerSurname(String surname);

}
