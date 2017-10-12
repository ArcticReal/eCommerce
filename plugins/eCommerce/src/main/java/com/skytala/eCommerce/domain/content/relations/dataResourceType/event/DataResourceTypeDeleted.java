package com.skytala.eCommerce.domain.content.relations.dataResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceType.model.DataResourceType;
public class DataResourceTypeDeleted implements Event{

	private boolean success;

	public DataResourceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
