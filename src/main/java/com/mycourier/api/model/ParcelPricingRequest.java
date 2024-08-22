package com.mycourier.api.model;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.mycourier.api.model.validator.ValidParcel;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Validated
@ValidParcel
public class ParcelPricingRequest {
	
	@NotNull(message = "Parcel Dimension List must not be null")
	List<Dimension> parcelDimensions;
	

	String voucherCode;
	
}
