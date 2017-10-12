package com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationSpot.model.AccommodationSpot;
public class AccommodationSpotDeleted implements Event{

	private boolean success;

	public AccommodationSpotDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
