package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
public class PartyTaxAuthInfoAdded implements Event{

	private PartyTaxAuthInfo addedPartyTaxAuthInfo;
	private boolean success;

	public PartyTaxAuthInfoAdded(PartyTaxAuthInfo addedPartyTaxAuthInfo, boolean success){
		this.addedPartyTaxAuthInfo = addedPartyTaxAuthInfo;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyTaxAuthInfo getAddedPartyTaxAuthInfo() {
		return addedPartyTaxAuthInfo;
	}

}
