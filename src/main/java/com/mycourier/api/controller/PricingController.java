package com.mycourier.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycourier.api.model.ParcelPricingRequest;
import com.mycourier.api.model.ParcelPricingResponse;
import com.mycourier.service.PricingService;

@RestController
@RequestMapping("/api/v1/pricing")
public class PricingController {
	
	
	private PricingService pricingService;
	

	public PricingController(PricingService pricingService) {
		this.pricingService = pricingService;
	}
	
	
	@GetMapping("/calculate-delivery-cost")
	public ResponseEntity<ParcelPricingResponse> getPrice(@RequestParam double weight, @RequestParam double height, @RequestParam double width, @RequestParam double length) {
		ParcelPricingRequest parcelRequest = new ParcelPricingRequest();
		ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/calculate-delivery-cost")
	public ResponseEntity<ParcelPricingResponse> getPrice(@RequestBody ParcelPricingRequest parcelRequest) {		
		ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
		return ResponseEntity.ok(response);
	}

}
