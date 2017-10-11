package com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.model.PartyGeoPoint;
public class PartyGeoPointDeleted implements Event{

	private boolean success;

	public PartyGeoPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
