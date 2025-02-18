package pl.pbarczewski.rest.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import java.util.List;


@NoArgsConstructor
@Data
@SuperBuilder
public class ResponseBody {
    protected HttpStatus httpStatus;
    protected List<String> msg;
    protected String createdObjectUuid;
}
