package com.skytala.eCommerce.domain.content.relations.contentRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRole.model.ContentRole;
public class ContentRoleUpdated implements Event{

	private boolean success;

	public ContentRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
