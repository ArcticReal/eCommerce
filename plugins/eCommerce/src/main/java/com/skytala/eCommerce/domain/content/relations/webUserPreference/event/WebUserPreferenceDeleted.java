package com.skytala.eCommerce.domain.content.relations.webUserPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
public class WebUserPreferenceDeleted implements Event{

	private boolean success;

	public WebUserPreferenceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
