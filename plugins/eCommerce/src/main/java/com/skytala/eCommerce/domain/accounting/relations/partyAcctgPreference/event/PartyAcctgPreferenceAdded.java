package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
public class PartyAcctgPreferenceAdded implements Event{

	private PartyAcctgPreference addedPartyAcctgPreference;
	private boolean success;

	public PartyAcctgPreferenceAdded(PartyAcctgPreference addedPartyAcctgPreference, boolean success){
		this.addedPartyAcctgPreference = addedPartyAcctgPreference;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyAcctgPreference getAddedPartyAcctgPreference() {
		return addedPartyAcctgPreference;
	}

}
