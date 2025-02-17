package pl.pbarczewski.infrastructure.mapper;

import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CreditViewModel;
import pl.pbarczewski.infrastructure.model.Credit;
import pl.pbarczewski.infrastructure.model.CreditView;

public class CreditMapper {
    public static Credit convertToCredit(String name, String number) {
        return Credit
                .builder()
                .name(name)
                .creditNumber(number)
                .build();
    }

    public static CreditViewModel convertToCreditViewModel(CreditView creditView) {
        return CreditViewModel
                .builder()



                .build();
    }

}
