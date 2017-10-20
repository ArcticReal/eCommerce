package com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
public class PartyQualTypeDeleted implements Event{

	private boolean success;

	public PartyQualTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
