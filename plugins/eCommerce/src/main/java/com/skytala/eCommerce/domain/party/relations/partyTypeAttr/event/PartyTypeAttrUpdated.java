package com.skytala.eCommerce.domain.party.relations.partyTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyTypeAttr.model.PartyTypeAttr;
public class PartyTypeAttrUpdated implements Event{

	private boolean success;

	public PartyTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
