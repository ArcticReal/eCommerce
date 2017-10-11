package com.skytala.eCommerce.domain.party.relations.partyClassification.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyClassification.model.PartyClassification;
public class PartyClassificationAdded implements Event{

	private PartyClassification addedPartyClassification;
	private boolean success;

	public PartyClassificationAdded(PartyClassification addedPartyClassification, boolean success){
		this.addedPartyClassification = addedPartyClassification;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyClassification getAddedPartyClassification() {
		return addedPartyClassification;
	}

}
