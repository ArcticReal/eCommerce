package com.skytala.eCommerce.domain.party.relations.party.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.typeAttr.PartyTypeAttr;
public class PartyTypeAttrDeleted implements Event{

	private boolean success;

	public PartyTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
