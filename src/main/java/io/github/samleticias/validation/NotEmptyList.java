package io.github.samleticias.validation;

import io.github.samleticias.validation.constraintvalidation.NotEmptyListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // tempo de execução
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

// toda annotation customizada de validação deve incluir esses três métodos
    String message() default "A lista pode ser vazia.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default  {};

}
