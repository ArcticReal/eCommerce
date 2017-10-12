package com.skytala.eCommerce.domain.humanres.relations.benefitType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.benefitType.model.BenefitType;
public class BenefitTypeFound implements Event{

	private List<BenefitType> benefitTypes;

	public BenefitTypeFound(List<BenefitType> benefitTypes) {
		this.benefitTypes = benefitTypes;
	}

	public List<BenefitType> getBenefitTypes()	{
		return benefitTypes;
	}

}
