package pl.pbarczewski.infrastructure.mapper;

import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.infrastructure.model.Customer;

public class CustomerMapper {

    public static Customer convertToCustomer(String creditNumber, CustomerModel customerModel) {
        return Customer
                .builder()
                .creditNumber(creditNumber)
                .firstName(customerModel.getName())
                .surname(customerModel.getSurname())
                .pesel(customerModel.getPesel())
                .build();
    }
}
