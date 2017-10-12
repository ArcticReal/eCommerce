package com.skytala.eCommerce.domain.content.relations.webSiteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
public class WebSiteRoleDeleted implements Event{

	private boolean success;

	public WebSiteRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
