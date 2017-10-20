package com.skytala.eCommerce.domain.party.relations.contactMech.event.party;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
public class PartyContactMechFound implements Event{

	private List<PartyContactMech> partyContactMechs;

	public PartyContactMechFound(List<PartyContactMech> partyContactMechs) {
		this.partyContactMechs = partyContactMechs;
	}

	public List<PartyContactMech> getPartyContactMechs()	{
		return partyContactMechs;
	}

}
