package com.mycourier.api.model.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PricingRule {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
	
    String ruleName;
	
    Integer rulePriority;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rule_condition_id")
    List<RuleCondition> conditions;
    
    Double costMultiplier;
    
    String costDimensionBasis;

}



