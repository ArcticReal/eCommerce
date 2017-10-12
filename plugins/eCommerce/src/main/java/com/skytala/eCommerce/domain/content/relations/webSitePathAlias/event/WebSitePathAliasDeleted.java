package com.skytala.eCommerce.domain.content.relations.webSitePathAlias.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePathAlias.model.WebSitePathAlias;
public class WebSitePathAliasDeleted implements Event{

	private boolean success;

	public WebSitePathAliasDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
