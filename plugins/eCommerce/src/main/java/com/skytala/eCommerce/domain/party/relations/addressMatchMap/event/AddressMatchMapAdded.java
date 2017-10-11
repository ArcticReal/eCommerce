package com.skytala.eCommerce.domain.party.relations.addressMatchMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
public class AddressMatchMapAdded implements Event{

	private AddressMatchMap addedAddressMatchMap;
	private boolean success;

	public AddressMatchMapAdded(AddressMatchMap addedAddressMatchMap, boolean success){
		this.addedAddressMatchMap = addedAddressMatchMap;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public AddressMatchMap getAddedAddressMatchMap() {
		return addedAddressMatchMap;
	}

}
