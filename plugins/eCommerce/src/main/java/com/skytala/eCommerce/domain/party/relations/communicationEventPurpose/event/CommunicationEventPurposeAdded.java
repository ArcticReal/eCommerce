package com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEventPurpose.model.CommunicationEventPurpose;
public class CommunicationEventPurposeAdded implements Event{

	private CommunicationEventPurpose addedCommunicationEventPurpose;
	private boolean success;

	public CommunicationEventPurposeAdded(CommunicationEventPurpose addedCommunicationEventPurpose, boolean success){
		this.addedCommunicationEventPurpose = addedCommunicationEventPurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventPurpose getAddedCommunicationEventPurpose() {
		return addedCommunicationEventPurpose;
	}

}
