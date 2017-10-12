package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
public class WebAnalyticsConfigUpdated implements Event{

	private boolean success;

	public WebAnalyticsConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
