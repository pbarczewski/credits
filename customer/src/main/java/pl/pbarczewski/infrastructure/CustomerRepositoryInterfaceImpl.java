package pl.pbarczewski.infrastructure;

import org.springframework.stereotype.Repository;
import pl.pbarczewski.domain.CustomerRepositoryInterface;
import pl.pbarczewski.domain.model.CustomerModel;

import java.util.List;

@Repository
public class CustomerRepositoryInterfaceImpl implements CustomerRepositoryInterface {

    @Override
    public List<CustomerModel> getCredits() {
        return null;
    }

    @Override
    public String createCredit() {
        return null;
    }
}
