package com.skytala.eCommerce.domain.accounting.relations.partyRate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyRate.model.PartyRate;
public class PartyRateDeleted implements Event{

	private boolean success;

	public PartyRateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
