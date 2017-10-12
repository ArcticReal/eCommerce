package com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.workeffort.relations.communicationEventWorkEff.model.CommunicationEventWorkEff;
public class CommunicationEventWorkEffFound implements Event{

	private List<CommunicationEventWorkEff> communicationEventWorkEffs;

	public CommunicationEventWorkEffFound(List<CommunicationEventWorkEff> communicationEventWorkEffs) {
		this.communicationEventWorkEffs = communicationEventWorkEffs;
	}

	public List<CommunicationEventWorkEff> getCommunicationEventWorkEffs()	{
		return communicationEventWorkEffs;
	}

}
