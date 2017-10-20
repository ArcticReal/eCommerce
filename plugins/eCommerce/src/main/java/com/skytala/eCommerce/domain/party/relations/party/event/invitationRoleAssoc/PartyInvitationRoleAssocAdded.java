package com.skytala.eCommerce.domain.party.relations.party.event.invitationRoleAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.invitationRoleAssoc.PartyInvitationRoleAssoc;
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
