package com.skytala.eCommerce.domain.humanres.relations.benefitType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.benefitType.model.BenefitType;
public class BenefitTypeUpdated implements Event{

	private boolean success;

	public BenefitTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
