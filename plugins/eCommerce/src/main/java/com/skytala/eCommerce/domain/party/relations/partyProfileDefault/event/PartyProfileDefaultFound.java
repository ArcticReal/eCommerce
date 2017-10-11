package com.skytala.eCommerce.domain.party.relations.partyProfileDefault.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyProfileDefault.model.PartyProfileDefault;
public class PartyProfileDefaultFound implements Event{

	private List<PartyProfileDefault> partyProfileDefaults;

	public PartyProfileDefaultFound(List<PartyProfileDefault> partyProfileDefaults) {
		this.partyProfileDefaults = partyProfileDefaults;
	}

	public List<PartyProfileDefault> getPartyProfileDefaults()	{
		return partyProfileDefaults;
	}

}
