package com.mycourier.api.model;

import com.mycourier.api.model.enums.DimensionType;
import com.mycourier.api.model.enums.Operations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PricingRuleEnum {
	
	REJECT("REJECT", 1, "Reject Parcel", null,   DimensionType.WEIGHT, DimensionType.WEIGHT, Operations.GREATER_THAN, 50.00),
	HEAVY_PARCEL("HEAVY", 2,"Heave Parcel", 20.00,  DimensionType.WEIGHT, DimensionType.WEIGHT, Operations.GREATER_THAN, 10.00),
	SMALL_PARCEL("SMALL", 3, "Small Parcel", 0.03, DimensionType.VOLUME, DimensionType.VOLUME, Operations.LESS_THAN, 1500.00),
	MEDIUM_PARCEL("MEDIUM", 4, "Medium Parcel", 0.04, DimensionType.VOLUME, DimensionType.VOLUME, Operations.LESS_THAN, 2500.00),
	LARGE_PARCEL("LARGE", 5, "Large Parcel", 0.05, DimensionType.VOLUME, DimensionType.VOLUME, Operations.NO_OPERATIONS, null);
	
	String ruleName;
	Integer priority;
	String description;
	Double costMultiplier;
	DimensionType costDimensionBasis;
	
    DimensionType dimensionField;
    Operations operation; // e.g., ">", "<", "=="
    Double dimensionOperand;

}
