package com.skytala.eCommerce.domain.order.relations.respondingParty.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
public class RespondingPartyAdded implements Event{

	private RespondingParty addedRespondingParty;
	private boolean success;

	public RespondingPartyAdded(RespondingParty addedRespondingParty, boolean success){
		this.addedRespondingParty = addedRespondingParty;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public RespondingParty getAddedRespondingParty() {
		return addedRespondingParty;
	}

}
