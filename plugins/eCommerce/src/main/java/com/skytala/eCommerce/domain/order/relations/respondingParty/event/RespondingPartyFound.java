package com.skytala.eCommerce.domain.order.relations.respondingParty.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.respondingParty.model.RespondingParty;
public class RespondingPartyFound implements Event{

	private List<RespondingParty> respondingPartys;

	public RespondingPartyFound(List<RespondingParty> respondingPartys) {
		this.respondingPartys = respondingPartys;
	}

	public List<RespondingParty> getRespondingPartys()	{
		return respondingPartys;
	}

}
