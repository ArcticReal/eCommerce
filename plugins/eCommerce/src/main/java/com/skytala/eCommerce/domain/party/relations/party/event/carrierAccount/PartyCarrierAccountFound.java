package com.skytala.eCommerce.domain.party.relations.party.event.carrierAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.carrierAccount.PartyCarrierAccount;
public class PartyCarrierAccountFound implements Event{

	private List<PartyCarrierAccount> partyCarrierAccounts;

	public PartyCarrierAccountFound(List<PartyCarrierAccount> partyCarrierAccounts) {
		this.partyCarrierAccounts = partyCarrierAccounts;
	}

	public List<PartyCarrierAccount> getPartyCarrierAccounts()	{
		return partyCarrierAccounts;
	}

}
