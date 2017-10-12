package com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyAcctgPreference.model.PartyAcctgPreference;
public class PartyAcctgPreferenceFound implements Event{

	private List<PartyAcctgPreference> partyAcctgPreferences;

	public PartyAcctgPreferenceFound(List<PartyAcctgPreference> partyAcctgPreferences) {
		this.partyAcctgPreferences = partyAcctgPreferences;
	}

	public List<PartyAcctgPreference> getPartyAcctgPreferences()	{
		return partyAcctgPreferences;
	}

}
