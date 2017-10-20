package com.skytala.eCommerce.domain.content.relations.dataResource.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.attribute.DataResourceAttribute;
public class DataResourceAttributeUpdated implements Event{

	private boolean success;

	public DataResourceAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
