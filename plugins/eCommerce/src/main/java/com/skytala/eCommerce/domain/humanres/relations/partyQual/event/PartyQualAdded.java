package com.skytala.eCommerce.domain.humanres.relations.partyQual.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.humanres.relations.partyQual.model.PartyQual;
public class PartyQualAdded implements Event{

	private PartyQual addedPartyQual;
	private boolean success;

	public PartyQualAdded(PartyQual addedPartyQual, boolean success){
		this.addedPartyQual = addedPartyQual;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyQual getAddedPartyQual() {
		return addedPartyQual;
	}

}
