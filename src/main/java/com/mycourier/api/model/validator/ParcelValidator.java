package com.mycourier.api.model.validator;

import java.util.List;

import com.mycourier.api.model.Dimension;
import com.mycourier.api.model.ParcelPricingRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ParcelValidator implements ConstraintValidator<ValidParcel, ParcelPricingRequest> {

	@Override
	public boolean isValid(ParcelPricingRequest parcelPricingRequest, ConstraintValidatorContext context) {
		
		List<Dimension> dimensions = parcelPricingRequest.getParcelDimensions();
		
		if (dimensions == null) {
			return false;
		}
		
		for (Dimension dim : dimensions) {
			double value = dim.getDimensionValue();		
			if (value <= 0) {
				return false;
			}
		}
				
		return true;
	}

}
