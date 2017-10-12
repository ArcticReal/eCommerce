package com.skytala.eCommerce.domain.accounting.relations.accommodationClass.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.accommodationClass.model.AccommodationClass;
public class AccommodationClassDeleted implements Event{

	private boolean success;

	public AccommodationClassDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
