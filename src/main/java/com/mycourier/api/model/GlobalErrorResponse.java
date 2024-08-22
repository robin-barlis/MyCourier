package com.mycourier.api.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlobalErrorResponse {
	
    String message;
    String details;
    
    Map<String, String> errors;

}
