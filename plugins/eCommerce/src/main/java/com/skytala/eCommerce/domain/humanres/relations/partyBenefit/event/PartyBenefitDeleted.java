package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
public class PartyBenefitDeleted implements Event{

	private boolean success;

	public PartyBenefitDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
