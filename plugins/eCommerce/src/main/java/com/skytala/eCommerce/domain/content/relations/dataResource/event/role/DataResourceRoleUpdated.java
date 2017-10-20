package com.skytala.eCommerce.domain.content.relations.dataResource.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.role.DataResourceRole;
public class DataResourceRoleUpdated implements Event{

	private boolean success;

	public DataResourceRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
