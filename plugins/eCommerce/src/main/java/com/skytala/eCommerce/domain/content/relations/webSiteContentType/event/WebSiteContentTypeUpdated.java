package com.skytala.eCommerce.domain.content.relations.webSiteContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteContentType.model.WebSiteContentType;
public class WebSiteContentTypeUpdated implements Event{

	private boolean success;

	public WebSiteContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
