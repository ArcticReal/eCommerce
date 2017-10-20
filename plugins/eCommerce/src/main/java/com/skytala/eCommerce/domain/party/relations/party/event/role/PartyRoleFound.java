package com.skytala.eCommerce.domain.party.relations.party.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.role.PartyRole;
public class PartyRoleFound implements Event{

	private List<PartyRole> partyRoles;

	public PartyRoleFound(List<PartyRole> partyRoles) {
		this.partyRoles = partyRoles;
	}

	public List<PartyRole> getPartyRoles()	{
		return partyRoles;
	}

}
