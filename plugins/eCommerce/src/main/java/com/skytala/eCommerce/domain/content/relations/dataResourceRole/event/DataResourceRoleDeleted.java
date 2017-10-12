package com.skytala.eCommerce.domain.content.relations.dataResourceRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResourceRole.model.DataResourceRole;
public class DataResourceRoleDeleted implements Event{

	private boolean success;

	public DataResourceRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
