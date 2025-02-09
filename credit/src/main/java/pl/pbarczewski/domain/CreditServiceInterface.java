package pl.pbarczewski.domain;

import pl.pbarczewski.infrastructure.model.Credit;

import java.util.List;

public interface CreditServiceInterface {
    List<Credit> getCredits();
    String generateNumber();
}
