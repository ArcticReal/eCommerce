package com.skytala.eCommerce.domain.party.relations.party.event.geoPoint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.party.model.geoPoint.PartyGeoPoint;
public class PartyGeoPointDeleted implements Event{

	private boolean success;

	public PartyGeoPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
