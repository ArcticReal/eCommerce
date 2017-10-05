package com.skytala.eCommerce.domain.accommodationClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accommodationClass.model.AccommodationClass;
public class AccommodationClassDeleted implements Event{

	private boolean success;

	public AccommodationClassDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
