package pl.pbarczewski.common;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationObject {

    private ValidateStatus validateStatus;
    private List<String> msg = new ArrayList<>();

    public void addMsg(String msg) {
        if(!msg.isEmpty())
            this.msg.add(msg);
    }
}
