package com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSitePublishPoint.model.WebSitePublishPoint;
public class WebSitePublishPointFound implements Event{

	private List<WebSitePublishPoint> webSitePublishPoints;

	public WebSitePublishPointFound(List<WebSitePublishPoint> webSitePublishPoints) {
		this.webSitePublishPoints = webSitePublishPoints;
	}

	public List<WebSitePublishPoint> getWebSitePublishPoints()	{
		return webSitePublishPoints;
	}

}
