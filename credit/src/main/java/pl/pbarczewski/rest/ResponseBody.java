package pl.pbarczewski.rest;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ResponseBody {
    HttpStatus httpStatus;
    List<String> msg;
    String createdObjectUuid;
}
