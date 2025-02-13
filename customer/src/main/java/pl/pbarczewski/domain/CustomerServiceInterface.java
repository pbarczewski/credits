package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.CustomerModel;
import java.util.List;

public interface CustomerServiceInterface {
    List<CustomerModel> getCredits();

    String saveCustomer(String creditNumber, CustomerModel customerModel);

}
