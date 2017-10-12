package com.skytala.eCommerce.domain.content.relations.webAnalyticsType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webAnalyticsType.model.WebAnalyticsType;
public class WebAnalyticsTypeDeleted implements Event{

	private boolean success;

	public WebAnalyticsTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
