package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.CustomerModel;

import java.util.List;

public interface CustomerRepositoryInterface {
    List<CustomerModel> getCredits();

    String createCredit();

}
