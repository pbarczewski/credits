package pl.pbarczewski.domain;

import org.springframework.stereotype.Repository;
import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.infrastructure.model.Credit;

import java.util.List;


public interface CreditRepositoryInterface {
    List<Credit> getCredits();
    String generateNumber();
    String createCredit(CreditModel creditModel, CustomerModel customerModel, ProductModel productModel);
}
