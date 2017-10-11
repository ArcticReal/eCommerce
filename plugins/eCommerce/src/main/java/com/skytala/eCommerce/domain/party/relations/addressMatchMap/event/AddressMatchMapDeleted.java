package com.skytala.eCommerce.domain.party.relations.addressMatchMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
public class AddressMatchMapDeleted implements Event{

	private boolean success;

	public AddressMatchMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
