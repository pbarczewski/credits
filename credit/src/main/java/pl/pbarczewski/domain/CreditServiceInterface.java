package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.rest.request.CreditRequest;
import java.util.List;


public interface CreditServiceInterface {
    List<CustomerModel> getCredits();
    String createCredit(CreditRequest creditRequest);
}
