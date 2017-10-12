package com.skytala.eCommerce.domain.content.relations.dataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
public class DataResourceUpdated implements Event{

	private boolean success;

	public DataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
