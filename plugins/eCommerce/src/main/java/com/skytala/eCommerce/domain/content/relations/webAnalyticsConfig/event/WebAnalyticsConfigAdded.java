package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
public class WebAnalyticsConfigAdded implements Event{

	private WebAnalyticsConfig addedWebAnalyticsConfig;
	private boolean success;

	public WebAnalyticsConfigAdded(WebAnalyticsConfig addedWebAnalyticsConfig, boolean success){
		this.addedWebAnalyticsConfig = addedWebAnalyticsConfig;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebAnalyticsConfig getAddedWebAnalyticsConfig() {
		return addedWebAnalyticsConfig;
	}

}
