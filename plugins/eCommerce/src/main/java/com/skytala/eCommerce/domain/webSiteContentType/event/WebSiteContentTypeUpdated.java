package com.skytala.eCommerce.domain.webSiteContentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.webSiteContentType.model.WebSiteContentType;
public class WebSiteContentTypeUpdated implements Event{

	private boolean success;

	public WebSiteContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
