package com.skytala.eCommerce.domain.content.relations.dataResource.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.type.DataResourceType;
public class DataResourceTypeUpdated implements Event{

	private boolean success;

	public DataResourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
