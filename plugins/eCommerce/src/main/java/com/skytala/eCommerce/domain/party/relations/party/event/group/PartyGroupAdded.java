package com.skytala.eCommerce.domain.party.relations.party.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.group.PartyGroup;
public class PartyGroupAdded implements Event{

	private PartyGroup addedPartyGroup;
	private boolean success;

	public PartyGroupAdded(PartyGroup addedPartyGroup, boolean success){
		this.addedPartyGroup = addedPartyGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyGroup getAddedPartyGroup() {
		return addedPartyGroup;
	}

}
