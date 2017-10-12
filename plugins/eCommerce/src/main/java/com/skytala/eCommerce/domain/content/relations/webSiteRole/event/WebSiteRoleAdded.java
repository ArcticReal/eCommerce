package com.skytala.eCommerce.domain.content.relations.webSiteRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteRole.model.WebSiteRole;
public class WebSiteRoleAdded implements Event{

	private WebSiteRole addedWebSiteRole;
	private boolean success;

	public WebSiteRoleAdded(WebSiteRole addedWebSiteRole, boolean success){
		this.addedWebSiteRole = addedWebSiteRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSiteRole getAddedWebSiteRole() {
		return addedWebSiteRole;
	}

}
