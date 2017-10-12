package com.skytala.eCommerce.domain.humanres.relations.partyQual.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
public class PartyQualDeleted implements Event{

	private boolean success;

	public PartyQualDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
