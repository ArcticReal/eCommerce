package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypDeleted implements Event{

	private boolean success;

	public CommunicationEventPrpTypDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
