package com.skytala.eCommerce.domain.accounting.relations.accommodationMap.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationMap.model.type.AccommodationMapType;
public class AccommodationMapTypeDeleted implements Event{

	private boolean success;

	public AccommodationMapTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
