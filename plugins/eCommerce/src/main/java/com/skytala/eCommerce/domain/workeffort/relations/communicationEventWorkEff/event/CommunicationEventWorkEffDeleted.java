package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
public class CommunicationEventWorkEffDeleted implements Event{

	private boolean success;

	public CommunicationEventWorkEffDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
