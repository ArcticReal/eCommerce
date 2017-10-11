package com.skytala.eCommerce.domain.party.relations.partyGeoPoint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.partyGeoPoint.model.PartyGeoPoint;
public class PartyGeoPointFound implements Event{

	private List<PartyGeoPoint> partyGeoPoints;

	public PartyGeoPointFound(List<PartyGeoPoint> partyGeoPoints) {
		this.partyGeoPoints = partyGeoPoints;
	}

	public List<PartyGeoPoint> getPartyGeoPoints()	{
		return partyGeoPoints;
	}

}
