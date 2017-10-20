package com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
public class PartyInvitationGroupAssocFound implements Event{

	private List<PartyInvitationGroupAssoc> partyInvitationGroupAssocs;

	public PartyInvitationGroupAssocFound(List<PartyInvitationGroupAssoc> partyInvitationGroupAssocs) {
		this.partyInvitationGroupAssocs = partyInvitationGroupAssocs;
	}

	public List<PartyInvitationGroupAssoc> getPartyInvitationGroupAssocs()	{
		return partyInvitationGroupAssocs;
	}

}
