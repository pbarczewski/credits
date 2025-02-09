package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.infrastructure.model.Credit;

import java.util.List;

public interface CreditRepositoryInterface {
    List<Credit> getCredits();
    String createCredit(CreditModel creditModel, ProductModel productModel, CustomerModel customerModel);
    String generateNumber();
}
