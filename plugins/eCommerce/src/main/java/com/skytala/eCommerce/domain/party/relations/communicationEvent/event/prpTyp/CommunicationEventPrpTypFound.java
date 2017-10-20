package com.skytala.eCommerce.domain.party.relations.communicationEvent.event.prpTyp;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.communicationEvent.model.prpTyp.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypFound implements Event{

	private List<CommunicationEventPrpTyp> communicationEventPrpTyps;

	public CommunicationEventPrpTypFound(List<CommunicationEventPrpTyp> communicationEventPrpTyps) {
		this.communicationEventPrpTyps = communicationEventPrpTyps;
	}

	public List<CommunicationEventPrpTyp> getCommunicationEventPrpTyps()	{
		return communicationEventPrpTyps;
	}

}
