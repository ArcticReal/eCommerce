package com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.partyPurpose.PartyContactMechPurpose;
public class PartyContactMechPurposeAdded implements Event{

	private PartyContactMechPurpose addedPartyContactMechPurpose;
	private boolean success;

	public PartyContactMechPurposeAdded(PartyContactMechPurpose addedPartyContactMechPurpose, boolean success){
		this.addedPartyContactMechPurpose = addedPartyContactMechPurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyContactMechPurpose getAddedPartyContactMechPurpose() {
		return addedPartyContactMechPurpose;
	}

}
