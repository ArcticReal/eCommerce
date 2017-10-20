package com.skytala.eCommerce.domain.party.relations.party.event.profileDefault;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
public class PartyProfileDefaultFound implements Event{

	private List<PartyProfileDefault> partyProfileDefaults;

	public PartyProfileDefaultFound(List<PartyProfileDefault> partyProfileDefaults) {
		this.partyProfileDefaults = partyProfileDefaults;
	}

	public List<PartyProfileDefault> getPartyProfileDefaults()	{
		return partyProfileDefaults;
	}

}
