package io.doubleloop.driverexplicit.adapter;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PivaRequiredIfBusinessValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PivaRequiredIfBusiness {
  String message() default "PIVA is required for business users";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
