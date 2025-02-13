package pl.pbarczewski.domain;

import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.rest.request.CreditRequest;
import java.util.List;


public interface CreditServiceInterface {
    List<Credit> getCredits();
    String createCredit(CreditRequest creditRequest);
}
