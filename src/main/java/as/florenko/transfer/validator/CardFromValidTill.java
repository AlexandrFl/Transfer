package as.florenko.transfer.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CardFromValidTillValidator.class)
@Documented
public @interface CardFromValidTill {

    String message() default "{cardFromValidTill.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
