package com.skytala.eCommerce.domain.party.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.model.Party;
public class PartyFound implements Event{

	private List<Party> partys;

	public PartyFound(List<Party> partys) {
		this.partys = partys;
	}

	public List<Party> getPartys()	{
		return partys;
	}

}
