package com.skytala.eCommerce.domain.party.relations.addressMatchMap.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addressMatchMap.model.AddressMatchMap;
public class AddressMatchMapFound implements Event{

	private List<AddressMatchMap> addressMatchMaps;

	public AddressMatchMapFound(List<AddressMatchMap> addressMatchMaps) {
		this.addressMatchMaps = addressMatchMaps;
	}

	public List<AddressMatchMap> getAddressMatchMaps()	{
		return addressMatchMaps;
	}

}
