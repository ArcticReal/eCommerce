package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
public class WebSitePathAliasUpdated implements Event{

	private boolean success;

	public WebSitePathAliasUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
