package com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocFound implements Event{

	private List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs;

	public PartyInvitationRoleAssocFound(List<PartyInvitationRoleAssoc> partyInvitationRoleAssocs) {
		this.partyInvitationRoleAssocs = partyInvitationRoleAssocs;
	}

	public List<PartyInvitationRoleAssoc> getPartyInvitationRoleAssocs()	{
		return partyInvitationRoleAssocs;
	}

}
