package com.skytala.eCommerce.domain.content.relations.dataResourceRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceRole.model.DataResourceRole;
public class DataResourceRoleUpdated implements Event{

	private boolean success;

	public DataResourceRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
