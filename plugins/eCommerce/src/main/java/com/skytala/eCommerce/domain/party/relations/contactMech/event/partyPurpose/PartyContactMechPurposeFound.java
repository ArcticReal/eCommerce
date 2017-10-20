package com.skytala.eCommerce.domain.party.relations.contactMech.event.partyPurpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.partyPurpose.PartyContactMechPurpose;
public class PartyContactMechPurposeFound implements Event{

	private List<PartyContactMechPurpose> partyContactMechPurposes;

	public PartyContactMechPurposeFound(List<PartyContactMechPurpose> partyContactMechPurposes) {
		this.partyContactMechPurposes = partyContactMechPurposes;
	}

	public List<PartyContactMechPurpose> getPartyContactMechPurposes()	{
		return partyContactMechPurposes;
	}

}
