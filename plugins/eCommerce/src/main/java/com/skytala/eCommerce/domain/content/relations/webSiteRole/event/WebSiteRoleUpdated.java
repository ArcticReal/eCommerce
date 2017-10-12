package com.skytala.eCommerce.domain.content.relations.webSiteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
public class WebSiteRoleUpdated implements Event{

	private boolean success;

	public WebSiteRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
