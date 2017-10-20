package com.skytala.eCommerce.domain.party.relations.party.event.profileDefault;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.profileDefault.PartyProfileDefault;
public class PartyProfileDefaultAdded implements Event{

	private PartyProfileDefault addedPartyProfileDefault;
	private boolean success;

	public PartyProfileDefaultAdded(PartyProfileDefault addedPartyProfileDefault, boolean success){
		this.addedPartyProfileDefault = addedPartyProfileDefault;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyProfileDefault getAddedPartyProfileDefault() {
		return addedPartyProfileDefault;
	}

}
