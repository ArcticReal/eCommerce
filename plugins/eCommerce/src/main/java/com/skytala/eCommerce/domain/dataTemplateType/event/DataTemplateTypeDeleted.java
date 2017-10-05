package com.skytala.eCommerce.domain.dataTemplateType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.dataTemplateType.model.DataTemplateType;
public class DataTemplateTypeDeleted implements Event{

	private boolean success;

	public DataTemplateTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
