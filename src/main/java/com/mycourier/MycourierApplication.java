package com.mycourier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mycourier.api.model.PricingRule;
import com.mycourier.api.model.PricingRuleEnum;
import com.mycourier.api.model.RuleCondition;
import com.mycourier.api.model.repository.RuleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class MycourierApplication implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private RuleRepository ruleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MycourierApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		createAndInsertRules();
	}

	private void createAndInsertRules() {
		
		List<PricingRule> rules = new ArrayList<PricingRule>(); 

		for (PricingRuleEnum currentPricingEnum : PricingRuleEnum.values()) {

			RuleCondition condition = new RuleCondition();
			condition.setDimensionField(currentPricingEnum.getDimensionField().name());
			condition.setDimensionOperand(currentPricingEnum.getDimensionOperand());
			condition.setOperation(currentPricingEnum.getOperation().name());

			PricingRule pricingRule = new PricingRule();
			pricingRule.setRuleName(currentPricingEnum.getRuleName());
			pricingRule.setCostMultiplier(currentPricingEnum.getCostMultiplier());
			pricingRule.setRulePriority(currentPricingEnum.getPriority());
			pricingRule.setConditions(Arrays.asList(condition));
			pricingRule.setCostDimensionBasis(currentPricingEnum.getCostDimensionBasis().name());
			rules.add(pricingRule);
		}
		ruleRepository.saveAll(rules);

	}

}
