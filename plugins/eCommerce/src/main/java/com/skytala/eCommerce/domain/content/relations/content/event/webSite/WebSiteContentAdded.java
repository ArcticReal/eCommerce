package com.skytala.eCommerce.domain.content.relations.content.event.webSite;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.webSite.WebSiteContent;
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
