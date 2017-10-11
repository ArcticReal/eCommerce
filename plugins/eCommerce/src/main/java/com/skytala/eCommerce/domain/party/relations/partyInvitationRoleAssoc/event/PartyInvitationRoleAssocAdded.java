package com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyInvitationRoleAssoc.model.PartyInvitationRoleAssoc;
public class PartyInvitationRoleAssocAdded implements Event{

	private PartyInvitationRoleAssoc addedPartyInvitationRoleAssoc;
	private boolean success;

	public PartyInvitationRoleAssocAdded(PartyInvitationRoleAssoc addedPartyInvitationRoleAssoc, boolean success){
		this.addedPartyInvitationRoleAssoc = addedPartyInvitationRoleAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyInvitationRoleAssoc getAddedPartyInvitationRoleAssoc() {
		return addedPartyInvitationRoleAssoc;
	}

}
