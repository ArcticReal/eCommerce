package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;
public class WebAnalyticsTypeFound implements Event{

	private List<WebAnalyticsType> webAnalyticsTypes;

	public WebAnalyticsTypeFound(List<WebAnalyticsType> webAnalyticsTypes) {
		this.webAnalyticsTypes = webAnalyticsTypes;
	}

	public List<WebAnalyticsType> getWebAnalyticsTypes()	{
		return webAnalyticsTypes;
	}

}
