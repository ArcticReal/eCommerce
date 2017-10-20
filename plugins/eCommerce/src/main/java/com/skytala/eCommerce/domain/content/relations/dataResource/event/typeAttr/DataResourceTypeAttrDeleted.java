package com.skytala.eCommerce.domain.content.relations.dataResource.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.typeAttr.DataResourceTypeAttr;
public class DataResourceTypeAttrDeleted implements Event{

	private boolean success;

	public DataResourceTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
