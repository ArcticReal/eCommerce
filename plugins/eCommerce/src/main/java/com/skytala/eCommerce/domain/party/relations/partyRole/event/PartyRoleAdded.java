package com.skytala.eCommerce.domain.party.relations.partyRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyRole.model.PartyRole;
public class PartyRoleAdded implements Event{

	private PartyRole addedPartyRole;
	private boolean success;

	public PartyRoleAdded(PartyRole addedPartyRole, boolean success){
		this.addedPartyRole = addedPartyRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyRole getAddedPartyRole() {
		return addedPartyRole;
	}

}