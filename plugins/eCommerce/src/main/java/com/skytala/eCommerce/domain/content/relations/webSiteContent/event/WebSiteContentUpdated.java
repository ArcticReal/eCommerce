package com.skytala.eCommerce.domain.content.relations.webSiteContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteContent.model.WebSiteContent;
public class WebSiteContentUpdated implements Event{

	private boolean success;

	public WebSiteContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
