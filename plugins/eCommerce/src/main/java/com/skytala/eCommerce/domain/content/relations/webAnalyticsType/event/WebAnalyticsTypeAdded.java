package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;
public class WebAnalyticsTypeAdded implements Event{

	private WebAnalyticsType addedWebAnalyticsType;
	private boolean success;

	public WebAnalyticsTypeAdded(WebAnalyticsType addedWebAnalyticsType, boolean success){
		this.addedWebAnalyticsType = addedWebAnalyticsType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebAnalyticsType getAddedWebAnalyticsType() {
		return addedWebAnalyticsType;
	}

}
