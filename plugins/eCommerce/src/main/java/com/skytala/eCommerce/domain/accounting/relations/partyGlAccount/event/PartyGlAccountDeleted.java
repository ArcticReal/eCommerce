package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
public class PartyGlAccountDeleted implements Event{

	private boolean success;

	public PartyGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
