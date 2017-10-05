package com.skytala.eCommerce.domain.benefitType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.benefitType.model.BenefitType;
public class BenefitTypeFound implements Event{

	private List<BenefitType> benefitTypes;

	public BenefitTypeFound(List<BenefitType> benefitTypes) {
		this.benefitTypes = benefitTypes;
	}

	public List<BenefitType> getBenefitTypes()	{
		return benefitTypes;
	}

}
