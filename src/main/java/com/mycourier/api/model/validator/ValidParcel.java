package com.mycourier.api.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {ParcelValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParcel {

    String message() default "Invalid Parcel. Please check the values and dimensions of your parcel.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
