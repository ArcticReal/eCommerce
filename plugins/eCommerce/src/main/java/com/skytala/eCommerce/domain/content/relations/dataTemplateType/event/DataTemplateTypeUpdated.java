package com.skytala.eCommerce.domain.content.relations.dataTemplateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataTemplateType.model.DataTemplateType;
public class DataTemplateTypeUpdated implements Event{

	private boolean success;

	public DataTemplateTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
