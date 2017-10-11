package com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.model.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocFound implements Event{

	private List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs;

	public PartyInvitationRoleAssocFound(List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs) {
		this.partyInvitationRoleAssocs = partyInvitationRoleAssocs;
	}

	public List<PartyInvitationRoleAssoc> getPartyInvitationRoleAssocs()	{
		return partyInvitationRoleAssocs;
	}

}
