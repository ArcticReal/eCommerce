package com.skytala.eCommerce.domain.content.relations.content.event.webSite;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.webSite.WebSiteContent;
public class WebSiteContentDeleted implements Event{

	private boolean success;

	public WebSiteContentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
