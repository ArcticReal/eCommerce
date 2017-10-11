package com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyCarrierAccount.model.PartyCarrierAccount;
public class PartyCarrierAccountAdded implements Event{

	private PartyCarrierAccount addedPartyCarrierAccount;
	private boolean success;

	public PartyCarrierAccountAdded(PartyCarrierAccount addedPartyCarrierAccount, boolean success){
		this.addedPartyCarrierAccount = addedPartyCarrierAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyCarrierAccount getAddedPartyCarrierAccount() {
		return addedPartyCarrierAccount;
	}

}
