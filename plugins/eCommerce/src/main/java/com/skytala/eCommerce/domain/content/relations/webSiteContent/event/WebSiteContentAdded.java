package com.skytala.eCommerce.domain.content.relations.webSiteContent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteContent.model.WebSiteContent;
public class WebSiteContentAdded implements Event{

	private WebSiteContent addedWebSiteContent;
	private boolean success;

	public WebSiteContentAdded(WebSiteContent addedWebSiteContent, boolean success){
		this.addedWebSiteContent = addedWebSiteContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSiteContent getAddedWebSiteContent() {
		return addedWebSiteContent;
	}

}
