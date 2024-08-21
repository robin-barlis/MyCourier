package com.mycourier.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mycourier.api.model.VoucherResponse;

@Service
public class VoucherService {

	@Value("${voucher.service.url}")
	private String voucherSerivceUrl; 
	
	@Value("${voucher.service.apikey}")
	private String apikey;

	public VoucherResponse getVoucherDetails(String voucherCode) {
		System.out.println(voucherSerivceUrl);
		String url = UriComponentsBuilder.fromHttpUrl(voucherSerivceUrl)
                .queryParam("code", voucherCode)
                .queryParam("apikey", apikey)
                .toUriString();
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, VoucherResponse.class);
	}

}
