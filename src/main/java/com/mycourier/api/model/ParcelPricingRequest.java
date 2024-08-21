package com.mycourier.api.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParcelPricingRequest {
	
	
	List<Dimension> parcelDimensions;
	
	String voucherCode;
	
}
