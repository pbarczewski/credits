package pl.pbarczewski.util.validator;



import org.springframework.stereotype.Component;
import pl.pbarczewski.domain.model.CustomerModel;

import java.util.List;

@Component
public class CreditValidator {

    /*private Pair<Boolean, List<String>> isCorrect(CustomerModel customerModel) {
        List<String> msg = new ArrayList<>();
        isModelEmpty(customerModel, msg);
        isPeselCorrect(customerModel.getPesel(), msg);
        return msg.isEmpty() ? new Pair<>(true, msg) : new Pair<>(false, msg);
    }*/

    private void isModelEmpty(CustomerModel customerModel, List<String> msg) {
        if(customerModel == null) {
            msg.add("Brak żądania");
        }
        if(customerModel.getFirstName().isEmpty()) {
            msg.add("Brakuje nazwy kredytu");
        }
        if(customerModel.getPesel().isEmpty()) {
            msg.add("Brakuje numeru PESEL");
        }
        if (customerModel.getSurname().isEmpty()) {
            msg.add("Brakuje nazwiska");
        }
    }

    private void isPeselCorrect(String pesel, List<String> msg) {
        if(pesel.length() != 11)
            msg.add("Numer PESEL jest nieprawidłowy");
        try {
            Integer.parseInt(pesel);
        } catch (NumberFormatException e) {
            msg.add("Numer PESEL nie jest liczbą");
        }
    }

    private boolean isCorrectName(String pesel) {
        if(pesel.length() != 11) return false;
        try {
            Integer.parseInt(pesel);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
