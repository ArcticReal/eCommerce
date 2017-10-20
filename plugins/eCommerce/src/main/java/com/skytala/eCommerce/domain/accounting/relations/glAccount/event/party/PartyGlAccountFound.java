package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.party.PartyGlAccount;
public class PartyGlAccountFound implements Event{

	private List<PartyGlAccount> partyGlAccounts;

	public PartyGlAccountFound(List<PartyGlAccount> partyGlAccounts) {
		this.partyGlAccounts = partyGlAccounts;
	}

	public List<PartyGlAccount> getPartyGlAccounts()	{
		return partyGlAccounts;
	}

}
