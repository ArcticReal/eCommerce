package com.skytala.eCommerce.domain.content.relations.content.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;
public class ContentRoleDeleted implements Event{

	private boolean success;

	public ContentRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
