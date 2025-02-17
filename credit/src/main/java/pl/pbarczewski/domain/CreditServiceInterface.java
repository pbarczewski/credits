package pl.pbarczewski.domain;

import pl.pbarczewski.domain.model.CreditViewModel;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.rest.request.CreditRequest;
import java.util.List;


public interface CreditServiceInterface {
    List<CreditViewModel> getCreditCompleteInfo();
    String createCredit(CreditRequest creditRequest);
    CreditViewModel findSingleCredit(String creditName);
    String getCreatedCreditNumber();
}
