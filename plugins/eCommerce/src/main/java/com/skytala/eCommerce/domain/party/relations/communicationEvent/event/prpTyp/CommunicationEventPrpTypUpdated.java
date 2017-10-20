package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypUpdated implements Event{

	private boolean success;

	public CommunicationEventPrpTypUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
