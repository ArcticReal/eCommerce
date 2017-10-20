package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypAdded implements Event{

	private CommunicationEventPrpTyp addedCommunicationEventPrpTyp;
	private boolean success;

	public CommunicationEventPrpTypAdded(CommunicationEventPrpTyp addedCommunicationEventPrpTyp, boolean success){
		this.addedCommunicationEventPrpTyp = addedCommunicationEventPrpTyp;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public CommunicationEventPrpTyp getAddedCommunicationEventPrpTyp() {
		return addedCommunicationEventPrpTyp;
	}

}
