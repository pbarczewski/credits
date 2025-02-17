package pl.pbarczewski.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.infrastructure.model.Credit;


@Repository
public interface CreditJpaRepository extends JpaRepository<Credit, Integer> {


}
