package pl.pbarczewski.util.validator;

import org.springframework.stereotype.Component;
import pl.pbarczewski.common.ValidationObject;
import pl.pbarczewski.domain.model.CreditModel;
import pl.pbarczewski.domain.model.CustomerModel;

@Component
public class CreditValidator {

    public ValidationObject validate(CreditModel creditModel) {
        ValidationObject validationObject = new ValidationObject();
        isNameEmpty(creditModel, validationObject);
        return validationObject;
    }


    private void isNameEmpty(CreditModel creditModel, ValidationObject validationObject) {
        if(creditModel.getName() == null || creditModel.getName().isEmpty()) {
            validationObject.addMsg("Pusta nazwa kredytu");
        }
    }

    private void isModelEmpty(CustomerModel customerModel, ValidationObject validationObject) {
        if(customerModel == null) {
            validationObject.addMsg("Brak żądania");
        }
        if(customerModel.getFirstName().isEmpty()) {
            validationObject.addMsg("Brakuje nazwy kredytu");
        }
        if(customerModel.getPesel().isEmpty()) {
            validationObject.addMsg("Brakuje numeru PESEL");
        }
        if (customerModel.getSurname().isEmpty()) {
            validationObject.addMsg("Brakuje nazwiska");
        }
    }

    private void isPeselCorrect(String pesel, ValidationObject validationObject) {
        if(pesel.length() != 11)
            validationObject.addMsg("Numer PESEL jest nieprawidłowy");
        try {
            Integer.parseInt(pesel);
        } catch (NumberFormatException e) {
            validationObject.addMsg("Numer PESEL nie jest liczbą");
        }
    }
}
