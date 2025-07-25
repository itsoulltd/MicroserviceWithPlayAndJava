package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationConfig {

    private static Logger LOG = LoggerFactory.getLogger(ValidationConfig.class);
    public static final String BEAN_VALIDATION_KEY = "Bean_Validation_Key";
    private static Validator defaultValidator;

    public static Validator getValidator() {
        if (defaultValidator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            defaultValidator = validator;
        }
        return defaultValidator;
    }

    public static <T> String[] validate(Validator validator, T target) {
        if (target == null) return new String[]{"target param must not be null!"};
        List<String> messages = new ArrayList<>();
        Set<ConstraintViolation<T>> violations = validator.validate(target);
        for (ConstraintViolation<T> violation : violations) {
            messages.add(violation.getMessage());
        }
        return messages.toArray(new String[0]);
    }

    public static <T> String[] validate(T target) {
        return validate(getValidator(), target);
    }

    public static <T> String validateWithMessage(Validator validator, T target) {
        String[] messages = validate(validator, target);
        return messages.length > 0
                ? String.join("; \n", messages)
                : null;
    }

    public static <T> String validateWithMessage(T target) {
        return validateWithMessage(getValidator(), target);
    }
}
