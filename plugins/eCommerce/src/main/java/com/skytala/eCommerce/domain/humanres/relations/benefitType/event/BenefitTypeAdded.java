package com.skytala.eCommerce.domain.humanres.relations.benefitType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.benefitType.model.BenefitType;
public class BenefitTypeAdded implements Event{

	private BenefitType addedBenefitType;
	private boolean success;

	public BenefitTypeAdded(BenefitType addedBenefitType, boolean success){
		this.addedBenefitType = addedBenefitType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BenefitType getAddedBenefitType() {
		return addedBenefitType;
	}

}
