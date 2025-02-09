package pl.pbarczewski.infrastructure.mapper;

import pl.pbarczewski.infrastructure.model.Credit;

public class CreditMapper {
    public static Credit convertToCredit(String name, String number) {
        return Credit
                .builder()
                .name(name)
                .creditNumber(number)
                .build();
    }


}
