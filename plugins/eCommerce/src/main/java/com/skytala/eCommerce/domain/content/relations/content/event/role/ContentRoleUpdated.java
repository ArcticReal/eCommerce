package com.skytala.eCommerce.domain.content.relations.content.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.role.ContentRole;
public class ContentRoleUpdated implements Event{

	private boolean success;

	public ContentRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
