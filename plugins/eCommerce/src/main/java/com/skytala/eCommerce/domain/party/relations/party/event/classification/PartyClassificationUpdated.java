package com.skytala.eCommerce.domain.party.relations.party.event.classification;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.classification.PartyClassification;
public class PartyClassificationUpdated implements Event{

	private boolean success;

	public PartyClassificationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
