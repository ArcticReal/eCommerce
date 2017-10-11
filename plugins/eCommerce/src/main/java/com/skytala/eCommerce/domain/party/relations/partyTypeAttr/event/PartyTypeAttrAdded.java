package com.skytala.eCommerce.domain.party.relations.partyTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyTypeAttr.model.PartyTypeAttr;
public class PartyTypeAttrAdded implements Event{

	private PartyTypeAttr addedPartyTypeAttr;
	private boolean success;

	public PartyTypeAttrAdded(PartyTypeAttr addedPartyTypeAttr, boolean success){
		this.addedPartyTypeAttr = addedPartyTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyTypeAttr getAddedPartyTypeAttr() {
		return addedPartyTypeAttr;
	}

}
