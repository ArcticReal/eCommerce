package com.skytala.eCommerce.domain.content.relations.content.event.webSiteType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.webSiteType.WebSiteContentType;
public class WebSiteContentTypeAdded implements Event{

	private WebSiteContentType addedWebSiteContentType;
	private boolean success;

	public WebSiteContentTypeAdded(WebSiteContentType addedWebSiteContentType, boolean success){
		this.addedWebSiteContentType = addedWebSiteContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebSiteContentType getAddedWebSiteContentType() {
		return addedWebSiteContentType;
	}

}
