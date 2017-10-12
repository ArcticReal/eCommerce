package com.skytala.eCommerce.domain.humanres.relations.partyResume.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyResume.model.PartyResume;
public class PartyResumeAdded implements Event{

	private PartyResume addedPartyResume;
	private boolean success;

	public PartyResumeAdded(PartyResume addedPartyResume, boolean success){
		this.addedPartyResume = addedPartyResume;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyResume getAddedPartyResume() {
		return addedPartyResume;
	}

}
