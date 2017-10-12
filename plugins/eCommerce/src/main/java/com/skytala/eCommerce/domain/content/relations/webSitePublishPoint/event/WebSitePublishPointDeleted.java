package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
public class WebSitePublishPointDeleted implements Event{

	private boolean success;

	public WebSitePublishPointDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
