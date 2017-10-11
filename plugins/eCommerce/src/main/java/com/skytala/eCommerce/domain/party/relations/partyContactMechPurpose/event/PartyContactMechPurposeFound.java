package com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyContactMechPurpose.model.PartyContactMechPurpose;
public class PartyContactMechPurposeFound implements Event{

	private List<PartyContactMechPurpose> partyContactMechPurposes;

	public PartyContactMechPurposeFound(List<PartyContactMechPurpose> partyContactMechPurposes) {
		this.partyContactMechPurposes = partyContactMechPurposes;
	}

	public List<PartyContactMechPurpose> getPartyContactMechPurposes()	{
		return partyContactMechPurposes;
	}

}
