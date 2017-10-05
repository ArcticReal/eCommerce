package com.skytala.eCommerce.domain.partyClassificationGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyClassificationGroup.model.PartyClassificationGroup;
public class PartyClassificationGroupUpdated implements Event{

	private boolean success;

	public PartyClassificationGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
