package com.skytala.eCommerce.domain.webSiteContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.webSiteContentType.model.WebSiteContentType;
public class WebSiteContentTypeDeleted implements Event{

	private boolean success;

	public WebSiteContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
