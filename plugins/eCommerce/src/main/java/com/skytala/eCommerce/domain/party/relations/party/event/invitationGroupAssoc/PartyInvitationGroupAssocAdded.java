package com.skytala.eCommerce.domain.party.relations.party.event.invitationGroupAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationGroupAssoc.PartyInvitationGroupAssoc;
public class PartyInvitationGroupAssocAdded implements Event{

	private PartyInvitationGroupAssoc addedPartyInvitationGroupAssoc;
	private boolean success;

	public PartyInvitationGroupAssocAdded(PartyInvitationGroupAssoc addedPartyInvitationGroupAssoc, boolean success){
		this.addedPartyInvitationGroupAssoc = addedPartyInvitationGroupAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyInvitationGroupAssoc getAddedPartyInvitationGroupAssoc() {
		return addedPartyInvitationGroupAssoc;
	}

}
