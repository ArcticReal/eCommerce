package com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyClassificationGroup.model.PartyClassificationGroup;
public class PartyClassificationGroupDeleted implements Event{

	private boolean success;

	public PartyClassificationGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
