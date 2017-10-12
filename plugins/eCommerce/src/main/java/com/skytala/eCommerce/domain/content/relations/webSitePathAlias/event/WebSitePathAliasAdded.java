package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
public class WebSitePathAliasAdded implements Event{

	private WebSitePathAlias addedWebSitePathAlias;
	private boolean success;

	public WebSitePathAliasAdded(WebSitePathAlias addedWebSitePathAlias, boolean success){
		this.addedWebSitePathAlias = addedWebSitePathAlias;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSitePathAlias getAddedWebSitePathAlias() {
		return addedWebSitePathAlias;
	}

}
