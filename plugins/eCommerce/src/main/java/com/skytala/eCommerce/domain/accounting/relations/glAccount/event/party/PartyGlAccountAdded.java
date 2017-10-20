package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.party;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.party.PartyGlAccount;
public class PartyGlAccountAdded implements Event{

	private PartyGlAccount addedPartyGlAccount;
	private boolean success;

	public PartyGlAccountAdded(PartyGlAccount addedPartyGlAccount, boolean success){
		this.addedPartyGlAccount = addedPartyGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyGlAccount getAddedPartyGlAccount() {
		return addedPartyGlAccount;
	}

}
