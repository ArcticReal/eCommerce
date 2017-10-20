package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.purpose.CommunicationEventPurpose;
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
