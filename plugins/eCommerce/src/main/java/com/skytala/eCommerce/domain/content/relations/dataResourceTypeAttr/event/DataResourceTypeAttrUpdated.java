package com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceTypeAttr.model.DataResourceTypeAttr;
public class DataResourceTypeAttrUpdated implements Event{

	private boolean success;

	public DataResourceTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
