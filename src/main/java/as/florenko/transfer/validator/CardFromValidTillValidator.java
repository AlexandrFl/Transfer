package as.florenko.transfer.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

class CardFromValidTillValidator implements ConstraintValidator<CardFromValidTill, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Date date = new Date();
        String[] d = value.split("/");
        int month = Integer.parseInt(d[0]);
        if (month > 12 || month < 1) {
            return false;
        }
        int inputYear = Integer.parseInt(d[1]);
        int y = (date.getYear() + 1900) % 1000;
        return inputYear >= y;
    }
}
