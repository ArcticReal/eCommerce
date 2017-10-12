package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
public class PartyAcctgPreferenceDeleted implements Event{

	private boolean success;

	public PartyAcctgPreferenceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
