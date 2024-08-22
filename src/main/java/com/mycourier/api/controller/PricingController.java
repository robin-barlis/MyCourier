package com.mycourier.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycourier.api.model.GlobalErrorResponse;
import com.mycourier.api.model.ParcelPricingRequest;
import com.mycourier.api.model.ParcelPricingResponse;
import com.mycourier.service.PricingService;
import com.mycourier.service.VoucherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pricing")
public class PricingController {
	
	private PricingService pricingService;
	

	public PricingController(PricingService pricingService, VoucherService voucherService) {
		this.pricingService = pricingService;
	}
	
//	@GetMapping("/delivery-cost")
//	public ResponseEntity<ParcelPricingResponse> getPrice1(@RequestBody ParcelPricingRequest parcelRequest) {
//		ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
//		
//		return ResponseEntity.ok(response);
//	}
	
	@Operation(summary = "Calculate the cost of a parcel.", description = "Depending on the parcel dimension, this API will calculate and return the delivery cost.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
        			 description = "Parcel Cost Calculated.", 
        			 content = @Content(mediaType = "application/json", 
        			 schema = @Schema(implementation = ParcelPricingResponse.class))),
        @ApiResponse(responseCode = "400",
   			 		 description = "Parcel Cost Calculated.", 
   			         content = @Content(mediaType = "application/json", 
   			         schema = @Schema(implementation = GlobalErrorResponse.class)))
    })
	@PostMapping("/delivery-cost")
	public ResponseEntity<ParcelPricingResponse> getPrice(@Valid @RequestBody ParcelPricingRequest parcelRequest) {
		ParcelPricingResponse response = pricingService.getPrice(parcelRequest);
		return ResponseEntity.ok(response);
	}

}
