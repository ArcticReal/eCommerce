package com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
public class PartyQualTypeUpdated implements Event{

	private boolean success;

	public PartyQualTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
