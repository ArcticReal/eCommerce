package com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.partyGlAccount.model.PartyGlAccount;
public class PartyGlAccountFound implements Event{

	private List<PartyGlAccount> partyGlAccounts;

	public PartyGlAccountFound(List<PartyGlAccount> partyGlAccounts) {
		this.partyGlAccounts = partyGlAccounts;
	}

	public List<PartyGlAccount> getPartyGlAccounts()	{
		return partyGlAccounts;
	}

}
