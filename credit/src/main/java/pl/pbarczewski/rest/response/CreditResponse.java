package pl.pbarczewski.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CreditViewModel;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponse extends ResponseBody {

    private CreditViewModel creditViewModel;

}
