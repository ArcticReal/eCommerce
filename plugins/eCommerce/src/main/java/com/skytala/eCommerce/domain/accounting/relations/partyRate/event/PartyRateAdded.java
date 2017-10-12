package com.skytala.eCommerce.domain.accounting.relations.partyRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
public class PartyRateAdded implements Event{

	private PartyRate addedPartyRate;
	private boolean success;

	public PartyRateAdded(PartyRate addedPartyRate, boolean success){
		this.addedPartyRate = addedPartyRate;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyRate getAddedPartyRate() {
		return addedPartyRate;
	}

}
