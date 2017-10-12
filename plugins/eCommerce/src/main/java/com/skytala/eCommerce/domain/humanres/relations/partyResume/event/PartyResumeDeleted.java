package com.skytala.eCommerce.domain.humanres.relations.partyResume.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
public class PartyResumeDeleted implements Event{

	private boolean success;

	public PartyResumeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
