package com.skytala.eCommerce.domain.party.relations.party.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;
public class PartyGeoPointAdded implements Event{

	private PartyGeoPoint addedPartyGeoPoint;
	private boolean success;

	public PartyGeoPointAdded(PartyGeoPoint addedPartyGeoPoint, boolean success){
		this.addedPartyGeoPoint = addedPartyGeoPoint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PartyGeoPoint getAddedPartyGeoPoint() {
		return addedPartyGeoPoint;
	}

}
