package com.skytala.eCommerce.domain.party.relations.party.event.classificationGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.classificationGroup.PartyClassificationGroup;
public class PartyClassificationGroupUpdated implements Event{

	private boolean success;

	public PartyClassificationGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
