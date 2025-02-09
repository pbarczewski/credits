package pl.pbarczewski.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CustomerModel;
import pl.pbarczewski.domain.model.ProductModel;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {
    private CreditModel creditModel;
    private ProductModel productModel;
    private CustomerModel customerModel;

}
