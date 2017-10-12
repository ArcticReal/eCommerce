package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
public class CommunicationEventWorkEffAdded implements Event{

	private CommunicationEventWorkEff addedCommunicationEventWorkEff;
	private boolean success;

	public CommunicationEventWorkEffAdded(CommunicationEventWorkEff addedCommunicationEventWorkEff, boolean success){
		this.addedCommunicationEventWorkEff = addedCommunicationEventWorkEff;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventWorkEff getAddedCommunicationEventWorkEff() {
		return addedCommunicationEventWorkEff;
	}

}
