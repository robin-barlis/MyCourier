package com.mycourier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycourier.api.model.Dimension;
import com.mycourier.api.model.ParcelPricingRequest;
import com.mycourier.api.model.ParcelPricingResponse;
import com.mycourier.api.model.PricingRule;
import com.mycourier.api.model.RuleCondition;
import com.mycourier.api.model.enums.DimensionType;
import com.mycourier.api.model.enums.Operations;
import com.mycourier.api.model.repository.RuleRepository;

@Service
public class PricingService {

	private RuleRepository ruleRepository;
	
	private static final String PRICE_NOT_AVAILABLE = "N/A";

	public PricingService(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}

	public ParcelPricingResponse getPrice(ParcelPricingRequest parcelRequest) {

		List<PricingRule> pricingRules = ruleRepository.findByOrderByRulePriorityAsc();

		Optional<PricingRule> matchingRule = pricingRules.stream().filter(rule -> matches(rule, parcelRequest)).findFirst();
		
		if (matchingRule.isPresent()) {
			PricingRule rule = matchingRule.get();
			Double cost = getCost(rule, parcelRequest);
			String costString = cost != null ? cost.toString() : PRICE_NOT_AVAILABLE;
			ParcelPricingResponse price = ParcelPricingResponse.builder().message(rule.getRuleName()).totalCost(costString).build();
			return price;
		}
		ParcelPricingResponse price = ParcelPricingResponse.builder().message("INVALID").totalCost("N/A").build();
		return price;
	}

	/**
	 * Determines if the {@code PricingRule} matches with the {@code Dimension} of
	 * the {@code ParcelPricingRequest}. If any of the condition is not passing, it
	 * will return false and will continue to the next rule.
	 * 
	 * @param pricingRule
	 * @param parcelRequest
	 * @return false if any of the conditions fails, or true if all conditions
	 *         passes
	 */
	private boolean matches(PricingRule pricingRule, ParcelPricingRequest parcelRequest) {
		boolean conditionsPass = true;

		for (RuleCondition condition : pricingRule.getConditions()) {
			
			double value = 0.0;
			DimensionType dimensionType = DimensionType.valueOf(condition.getDimensionField());	
			
			if (dimensionType != DimensionType.VOLUME) {
				Optional<Dimension> optionalDimension = parcelRequest.getParcelDimensions().stream()
						.filter(dim -> dim.getDimension().equalsIgnoreCase(condition.getDimensionField())).findFirst();
				
				
				if (optionalDimension.isPresent()) {
					Dimension dimension = optionalDimension.get();
					value = dimension.getDimensionValue();
				} else {
					conditionsPass = false;
				}
				
				
			} else if (dimensionType == DimensionType.VOLUME){
				value = getParcelVolume(parcelRequest.getParcelDimensions());	
			}
			conditionsPass = performMathOperations(condition, value);
			if (!conditionsPass) {
				break;
			}
		}

		return conditionsPass;

	}

	private boolean performMathOperations(RuleCondition condition, double value) {
		
		if (Operations.GREATER_THAN == Operations.valueOf(condition.getOperation())) {
			return value > condition.getDimensionOperand();
		} else if (Operations.LESS_THAN == Operations.valueOf(condition.getOperation())) {
			return value < condition.getDimensionOperand();
		} else if (Operations.GREATER_THAN_EQUALS == Operations.valueOf(condition.getOperation())) {
			return  value >= condition.getDimensionOperand();
		} else if (Operations.LESS_THAN_EQUALS == Operations.valueOf(condition.getOperation())) {
			return value <= condition.getDimensionOperand();
		} else if (Operations.EQUALS == Operations.valueOf(condition.getOperation())) {
			return value == condition.getDimensionOperand();
		} else {
			return true;
		}
	}

	private double getParcelVolume(List<Dimension> parcelDimensions) {
		double volume = 1.0;
		
		for (Dimension dim : parcelDimensions) {
			if (DimensionType.HEIGHT == (DimensionType.valueOf(dim.getDimension())) 
					|| DimensionType.LENGTH == (DimensionType.valueOf(dim.getDimension()))
					|| DimensionType.WIDTH == (DimensionType.valueOf(dim.getDimension())))
			volume *= dim.getDimensionValue();
		}
		
		System.out.println("VOLUME " + volume);
		return volume;
	}
	
	private double getParcelDimension(List<Dimension> parcelDimensions, DimensionType costDimensionBasis) {
		double weight = 0.00;
		
		for (Dimension dim : parcelDimensions) {
			if (costDimensionBasis == (DimensionType.valueOf(dim.getDimension()))) {

				weight = dim.getDimensionValue();
			}
		}
		
		System.out.println("VOLUME " + weight);
		return weight;
	}
	


	private Double getCost(PricingRule rule, ParcelPricingRequest parcelRequest) {
		if (rule.getCostMultiplier() == null || rule.getCostMultiplier() == 0.00) {
			return null;
		} 
		
		DimensionType costDimensionBasis = DimensionType.valueOf(rule.getCostDimensionBasis());
		if (DimensionType.VOLUME == costDimensionBasis) {

			Double volume = getParcelVolume(parcelRequest.getParcelDimensions());
			return rule.getCostMultiplier() * volume;
			
		}
		
		Double dimensionValue = getParcelDimension(parcelRequest.getParcelDimensions(), costDimensionBasis);
		
		return rule.getCostMultiplier() * dimensionValue;
	}

}
