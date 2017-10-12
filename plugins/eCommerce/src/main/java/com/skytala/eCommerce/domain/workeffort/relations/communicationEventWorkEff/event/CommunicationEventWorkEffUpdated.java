package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
public class CommunicationEventWorkEffUpdated implements Event{

	private boolean success;

	public CommunicationEventWorkEffUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
