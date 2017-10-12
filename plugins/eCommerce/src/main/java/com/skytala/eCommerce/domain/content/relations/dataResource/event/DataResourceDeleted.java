package com.skytala.eCommerce.domain.content.relations.dataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.DataResource;
public class DataResourceDeleted implements Event{

	private boolean success;

	public DataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
