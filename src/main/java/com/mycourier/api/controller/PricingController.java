package com.mycourier.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycourier.api.model.ParcelPricingRequest;
import com.mycourier.api.model.ParcelPricingResponse;
import com.mycourier.api.model.VoucherResponse;
import com.mycourier.service.PricingService;
import com.mycourier.service.VoucherService;

@RestController
@RequestMapping("/api/v1/pricing")
public class PricingController {
	
	private PricingService pricingService;
//	private VoucherService voucherService;
	

	public PricingController(PricingService pricingService, VoucherService voucherService) {
		this.pricingService = pricingService;
//		this.voucherService = voucherService;
	}
	
	
	@PostMapping("/delivery-cost")
	public ResponseEntity<ParcelPricingResponse> getPrice(@RequestBody ParcelPricingRequest parcelRequest) {
//		VoucherResponse voucherDetails = null;
//		if (parcelRequest.getVoucherCode() != null) {
//			voucherDetails = voucherService.getVoucherDetails(parcelRequest.getVoucherCode());
//		}
		ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
		
		return ResponseEntity.ok(response);
	}

}
