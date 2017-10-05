package com.skytala.eCommerce.domain.communicationEventPrpTyp.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.communicationEventPrpTyp.model.CommunicationEventPrpTyp;
public class CommunicationEventPrpTypFound implements Event{

	private List<CommunicationEventPrpTyp> communicationEventPrpTyps;

	public CommunicationEventPrpTypFound(List<CommunicationEventPrpTyp> communicationEventPrpTyps) {
		this.communicationEventPrpTyps = communicationEventPrpTyps;
	}

	public List<CommunicationEventPrpTyp> getCommunicationEventPrpTyps()	{
		return communicationEventPrpTyps;
	}

}
