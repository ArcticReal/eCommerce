package com.skytala.eCommerce.domain.partyInvitation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyInvitation.model.PartyInvitation;
public class PartyInvitationAdded implements Event{

	private PartyInvitation addedPartyInvitation;
	private boolean success;

	public PartyInvitationAdded(PartyInvitation addedPartyInvitation, boolean success){
		this.addedPartyInvitation = addedPartyInvitation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyInvitation getAddedPartyInvitation() {
		return addedPartyInvitation;
	}

}
