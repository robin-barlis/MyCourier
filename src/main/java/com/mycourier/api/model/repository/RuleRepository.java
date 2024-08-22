package com.mycourier.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycourier.api.model.dto.PricingRule;

public interface RuleRepository extends JpaRepository<PricingRule, Long> {
	List<PricingRule> findByOrderByRulePriorityAsc();


}
