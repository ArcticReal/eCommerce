package com.skytala.eCommerce.domain.party.relations.contactMech.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
public class PartyContactMechAdded implements Event{

	private PartyContactMech addedPartyContactMech;
	private boolean success;

	public PartyContactMechAdded(PartyContactMech addedPartyContactMech, boolean success){
		this.addedPartyContactMech = addedPartyContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyContactMech getAddedPartyContactMech() {
		return addedPartyContactMech;
	}

}
