package com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.model.DataResourceAttribute;
public class DataResourceAttributeUpdated implements Event{

	private boolean success;

	public DataResourceAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
