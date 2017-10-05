package com.skytala.eCommerce.domain.partyClassificationType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.partyClassificationType.model.PartyClassificationType;
public class PartyClassificationTypeUpdated implements Event{

	private boolean success;

	public PartyClassificationTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
