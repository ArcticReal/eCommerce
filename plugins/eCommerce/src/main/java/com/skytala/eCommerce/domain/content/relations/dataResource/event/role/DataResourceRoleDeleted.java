package com.skytala.eCommerce.domain.content.relations.dataResource.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.role.DataResourceRole;
public class DataResourceRoleDeleted implements Event{

	private boolean success;

	public DataResourceRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
