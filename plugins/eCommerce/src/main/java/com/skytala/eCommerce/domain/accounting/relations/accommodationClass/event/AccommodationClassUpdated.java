package com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;
public class AccommodationClassUpdated implements Event{

	private boolean success;

	public AccommodationClassUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
