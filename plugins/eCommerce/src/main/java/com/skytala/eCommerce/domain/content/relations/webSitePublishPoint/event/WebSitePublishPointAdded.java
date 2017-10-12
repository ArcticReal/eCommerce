package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
public class WebSitePublishPointAdded implements Event{

	private WebSitePublishPoint addedWebSitePublishPoint;
	private boolean success;

	public WebSitePublishPointAdded(WebSitePublishPoint addedWebSitePublishPoint, boolean success){
		this.addedWebSitePublishPoint = addedWebSitePublishPoint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSitePublishPoint getAddedWebSitePublishPoint() {
		return addedWebSitePublishPoint;
	}

}
