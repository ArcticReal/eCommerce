package com.skytala.eCommerce.domain.communicationEventPrpTyp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEventPrpTyp.model.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypUpdated implements Event{

	private boolean success;

	public CommunicationEventPrpTypUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
