package com.skytala.eCommerce.domain.webSiteContentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.webSiteContentType.model.WebSiteContentType;
public class WebSiteContentTypeFound implements Event{

	private List<WebSiteContentType> webSiteContentTypes;

	public WebSiteContentTypeFound(List<WebSiteContentType> webSiteContentTypes) {
		this.webSiteContentTypes = webSiteContentTypes;
	}

	public List<WebSiteContentType> getWebSiteContentTypes()	{
		return webSiteContentTypes;
	}

}
