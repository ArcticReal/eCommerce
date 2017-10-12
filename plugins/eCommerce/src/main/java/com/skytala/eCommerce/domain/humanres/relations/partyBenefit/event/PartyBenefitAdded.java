package com.skytala.eCommerce.domain.humanres.relations.partyBenefit.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyBenefit.model.PartyBenefit;
public class PartyBenefitAdded implements Event{

	private PartyBenefit addedPartyBenefit;
	private boolean success;

	public PartyBenefitAdded(PartyBenefit addedPartyBenefit, boolean success){
		this.addedPartyBenefit = addedPartyBenefit;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyBenefit getAddedPartyBenefit() {
		return addedPartyBenefit;
	}

}
