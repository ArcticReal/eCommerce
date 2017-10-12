package com.skytala.eCommerce.domain.humanres.relations.partyResume.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
public class PartyResumeUpdated implements Event{

	private boolean success;

	public PartyResumeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
