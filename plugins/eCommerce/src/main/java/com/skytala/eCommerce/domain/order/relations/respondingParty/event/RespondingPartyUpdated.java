package com.skytala.eCommerce.domain.order.relations.respondingParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
public class RespondingPartyUpdated implements Event{

	private boolean success;

	public RespondingPartyUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
