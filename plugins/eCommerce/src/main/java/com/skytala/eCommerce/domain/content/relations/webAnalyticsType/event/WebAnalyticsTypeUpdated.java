package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;
public class WebAnalyticsTypeUpdated implements Event{

	private boolean success;

	public WebAnalyticsTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
