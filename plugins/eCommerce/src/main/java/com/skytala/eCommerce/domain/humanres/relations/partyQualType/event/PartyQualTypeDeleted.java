package com.skytala.eCommerce.domain.humanres.relations.partyQualType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQualType.model.PartyQualType;
public class PartyQualTypeDeleted implements Event{

	private boolean success;

	public PartyQualTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
