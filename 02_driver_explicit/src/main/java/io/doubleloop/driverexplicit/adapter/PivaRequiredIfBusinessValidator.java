package io.doubleloop.driverexplicit.adapter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PivaRequiredIfBusinessValidator implements ConstraintValidator<PivaRequiredIfBusiness, RegisterUserRequest> {

  @Override
  public void initialize(PivaRequiredIfBusiness constraintAnnotation) {
  }

  @Override
  public boolean isValid(RegisterUserRequest request, ConstraintValidatorContext context) {
    if (request.isBusiness) {
      return request.PIVA != null && !request.PIVA.isEmpty();
    }
    return true;
  }
}
