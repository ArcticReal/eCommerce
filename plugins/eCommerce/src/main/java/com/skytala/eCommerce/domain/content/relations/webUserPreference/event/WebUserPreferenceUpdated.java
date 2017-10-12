package com.skytala.eCommerce.domain.content.relations.webUserPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
public class WebUserPreferenceUpdated implements Event{

	private boolean success;

	public WebUserPreferenceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
