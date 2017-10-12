package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.AccommodationMap;
public class AccommodationMapDeleted implements Event{

	private boolean success;

	public AccommodationMapDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
