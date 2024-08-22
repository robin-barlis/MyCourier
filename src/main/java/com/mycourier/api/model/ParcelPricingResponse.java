package com.mycourier.api.model;

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
	
	String message;

}
