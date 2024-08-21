package com.mycourier.api.model;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter 
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParcelPricingResponse {
	
	String totalCost;
	
	@NonNull 
	String message;

}
