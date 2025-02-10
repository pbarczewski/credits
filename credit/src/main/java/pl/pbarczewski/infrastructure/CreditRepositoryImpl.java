package pl.pbarczewski.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;
import pl.pbarczewski.infrastructure.mapper.CreditMapper;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.infrastructure.repository.CreditJpaRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CreditRepositoryImpl implements CreditRepositoryInterface {

    private CreditJpaRepository creditJpaRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    public CreditRepositoryImpl(CreditJpaRepository creditJpaRepository) {
        this.creditJpaRepository = creditJpaRepository;
    }

    @Override
    public List<Credit> getCredits() {
        return creditJpaRepository.findAll();
    }


    @Override
    public String generateNumber() {
        boolean isNotInDatabase = true;
        String creditCardNumber = generateCreditNumber();
        while (isNotInDatabase) {
            Credit credit = creditJpaRepository.getCreditByCreditNumber(creditCardNumber);
            if(credit == null) {
                isNotInDatabase = false;
            }
        }
        return creditCardNumber;
    }

    @Override
    public String createCredit(CreditModel creditModel, CustomerModel customerModel, ProductModel productModel) {
        return null;
    }


    private String generateCreditNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int index = ThreadLocalRandom.current().nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}
