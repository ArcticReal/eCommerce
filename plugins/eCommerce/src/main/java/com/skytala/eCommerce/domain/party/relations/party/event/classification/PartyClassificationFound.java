package com.skytala.eCommerce.domain.party.relations.party.event.classification;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
public class PartyClassificationFound implements Event{

	private List<PartyClassification> partyClassifications;

	public PartyClassificationFound(List<PartyClassification> partyClassifications) {
		this.partyClassifications = partyClassifications;
	}

	public List<PartyClassification> getPartyClassifications()	{
		return partyClassifications;
	}

}
