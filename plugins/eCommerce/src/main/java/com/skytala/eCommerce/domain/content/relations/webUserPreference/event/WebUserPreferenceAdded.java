package com.skytala.eCommerce.domain.content.relations.webUserPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
public class WebUserPreferenceAdded implements Event{

	private WebUserPreference addedWebUserPreference;
	private boolean success;

	public WebUserPreferenceAdded(WebUserPreference addedWebUserPreference, boolean success){
		this.addedWebUserPreference = addedWebUserPreference;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebUserPreference getAddedWebUserPreference() {
		return addedWebUserPreference;
	}

}
