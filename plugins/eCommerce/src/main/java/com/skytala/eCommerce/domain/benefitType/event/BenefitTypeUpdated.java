package com.skytala.eCommerce.domain.benefitType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.benefitType.model.BenefitType;
public class BenefitTypeUpdated implements Event{

	private boolean success;

	public BenefitTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
