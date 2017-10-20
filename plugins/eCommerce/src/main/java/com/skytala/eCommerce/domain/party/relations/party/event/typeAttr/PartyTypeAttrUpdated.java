package com.skytala.eCommerce.domain.party.relations.party.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;
public class PartyTypeAttrUpdated implements Event{

	private boolean success;

	public PartyTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
