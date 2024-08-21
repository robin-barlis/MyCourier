package com.mycourier.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycourier.api.model.ParcelPricingRequest;
import com.mycourier.service.PricingService;

@RestController
@RequestMapping("/api/v1/rules")
public class ConditionController {
	
	
	//private PricingService pricingService;
	

	public ConditionController(PricingService pricingService) {
		//this.pricingService = pricingService;
	}
	
	
	@PostMapping("/conditions")
	public ResponseEntity<String> getPrice(@RequestBody ParcelPricingRequest parcelRequest) {		
		//ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
		return ResponseEntity.ok("ok");
	}

}
