package com.skytala.eCommerce.domain.content.relations.content.event.webSiteType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.webSiteType.WebSiteContentType;
public class WebSiteContentTypeDeleted implements Event{

	private boolean success;

	public WebSiteContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
