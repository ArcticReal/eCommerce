package com.skytala.eCommerce.domain.communicationEventPrpTyp.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEventPrpTyp.model.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypDeleted implements Event{

	private boolean success;

	public CommunicationEventPrpTypDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
