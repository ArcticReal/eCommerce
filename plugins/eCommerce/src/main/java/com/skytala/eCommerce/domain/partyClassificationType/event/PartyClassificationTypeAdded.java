package com.skytala.eCommerce.domain.partyClassificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyClassificationType.model.PartyClassificationType;
public class PartyClassificationTypeAdded implements Event{

	private PartyClassificationType addedPartyClassificationType;
	private boolean success;

	public PartyClassificationTypeAdded(PartyClassificationType addedPartyClassificationType, boolean success){
		this.addedPartyClassificationType = addedPartyClassificationType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyClassificationType getAddedPartyClassificationType() {
		return addedPartyClassificationType;
	}

}
