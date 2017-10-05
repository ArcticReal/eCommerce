package com.skytala.eCommerce.domain.dataResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.dataResourceType.model.DataResourceType;
public class DataResourceTypeUpdated implements Event{

	private boolean success;

	public DataResourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
