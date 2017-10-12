package com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsConfig.model.WebAnalyticsConfig;
public class WebAnalyticsConfigDeleted implements Event{

	private boolean success;

	public WebAnalyticsConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
