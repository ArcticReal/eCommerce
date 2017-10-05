package com.skytala.eCommerce.domain.partyInvitation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyInvitation.model.PartyInvitation;
public class PartyInvitationFound implements Event{

	private List<PartyInvitation> partyInvitations;

	public PartyInvitationFound(List<PartyInvitation> partyInvitations) {
		this.partyInvitations = partyInvitations;
	}

	public List<PartyInvitation> getPartyInvitations()	{
		return partyInvitations;
	}

}
