package pl.pbarczewski.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.pbarczewski.domain.CreditRepositoryInterface;
import pl.pbarczewski.domain.model.CreditViewModel;
import pl.pbarczewski.infrastructure.mapper.CreditMapper;
import pl.pbarczewski.infrastructure.repository.CreditJpaRepository;
import pl.pbarczewski.infrastructure.repository.CreditViewJpaRepository;
import pl.pbarczewski.rest.request.CreditRequest;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Repository
public class CreditRepositoryImpl implements CreditRepositoryInterface {

    private CreditJpaRepository creditJpaRepository;

    private CreditViewJpaRepository creditViewJpaRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    public CreditRepositoryImpl(CreditJpaRepository creditJpaRepository, CreditViewJpaRepository creditViewJpaRepository) {
        this.creditJpaRepository = creditJpaRepository;
        this.creditViewJpaRepository = creditViewJpaRepository;
    }

    @Override
    public List<CreditViewModel> getCreditCompleteInfo() {
        return creditViewJpaRepository.findAll().stream().map(CreditMapper::convertToCreditViewModel).toList();
    }

    @Override
    public String createCredit(CreditRequest creditRequest) {
        return null;
    }

    @Override
    public CreditViewModel findSingleCredit(String creditName) {
        return null;
    }

    @Override
    public String getCreatedCreditNumber() {
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
