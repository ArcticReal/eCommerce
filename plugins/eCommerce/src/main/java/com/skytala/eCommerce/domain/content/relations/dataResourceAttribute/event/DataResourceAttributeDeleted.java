package com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceAttribute.model.DataResourceAttribute;
public class DataResourceAttributeDeleted implements Event{

	private boolean success;

	public DataResourceAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
