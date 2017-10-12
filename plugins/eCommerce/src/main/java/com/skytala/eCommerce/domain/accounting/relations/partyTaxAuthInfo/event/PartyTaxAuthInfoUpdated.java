package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
public class PartyTaxAuthInfoUpdated implements Event{

	private boolean success;

	public PartyTaxAuthInfoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
