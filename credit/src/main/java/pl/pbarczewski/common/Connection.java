package pl.pbarczewski.common;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.pbarczewski.rest.ResponseBody;
import pl.pbarczewski.rest.request.CreditRequest;
import pl.pbarczewski.util.validator.Url;

import java.util.Arrays;


@Component
public class Connection {
    public static ResponseEntity<ResponseBody> createConnection(Url url, HttpMethod httpMethod, HttpEntity<CreditRequest> httpEntity) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url.getUrl(), httpMethod, httpEntity, ResponseBody.class);
    }


    // todo Do zrobienia na potem
    public static HttpHeaders generateHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
