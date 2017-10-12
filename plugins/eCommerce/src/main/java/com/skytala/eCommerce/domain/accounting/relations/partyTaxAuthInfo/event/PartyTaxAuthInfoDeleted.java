package com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyTaxAuthInfo.model.PartyTaxAuthInfo;
public class PartyTaxAuthInfoDeleted implements Event{

	private boolean success;

	public PartyTaxAuthInfoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
