package pl.pbarczewski.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditViewModel {

    private String creditNumber;
    private String creditName;
    private String customerName;
    private String surname;
    private String pesel;
    private String productName;
}
