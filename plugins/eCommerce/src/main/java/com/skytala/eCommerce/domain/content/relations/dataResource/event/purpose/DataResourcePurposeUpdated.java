package com.skytala.eCommerce.domain.content.relations.dataResource.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.purpose.DataResourcePurpose;
public class DataResourcePurposeUpdated implements Event{

	private boolean success;

	public DataResourcePurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
