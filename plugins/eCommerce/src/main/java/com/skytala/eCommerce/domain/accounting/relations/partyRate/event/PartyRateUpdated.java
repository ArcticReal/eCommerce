package com.skytala.eCommerce.domain.accounting.relations.partyRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
public class PartyRateUpdated implements Event{

	private boolean success;

	public PartyRateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
