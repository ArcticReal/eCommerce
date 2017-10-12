package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
public class WebSitePublishPointUpdated implements Event{

	private boolean success;

	public WebSitePublishPointUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
