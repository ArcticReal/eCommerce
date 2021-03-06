package com.skytala.eCommerce.domain.humanres.relations.partyQual.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.type.PartyQualType;
public class PartyQualTypeAdded implements Event{

	private PartyQualType addedPartyQualType;
	private boolean success;

	public PartyQualTypeAdded(PartyQualType addedPartyQualType, boolean success){
		this.addedPartyQualType = addedPartyQualType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyQualType getAddedPartyQualType() {
		return addedPartyQualType;
	}

}
