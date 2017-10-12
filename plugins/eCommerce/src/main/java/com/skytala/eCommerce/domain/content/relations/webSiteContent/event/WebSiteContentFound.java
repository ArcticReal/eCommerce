package com.skytala.eCommerce.domain.content.relations.webSiteContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webSiteContent.model.WebSiteContent;
public class WebSiteContentFound implements Event{

	private List<WebSiteContent> webSiteContents;

	public WebSiteContentFound(List<WebSiteContent> webSiteContents) {
		this.webSiteContents = webSiteContents;
	}

	public List<WebSiteContent> getWebSiteContents()	{
		return webSiteContents;
	}

}
