package com.skytala.eCommerce.domain.content.relations.webUserPreference.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
public class WebUserPreferenceFound implements Event{

	private List<WebUserPreference> webUserPreferences;

	public WebUserPreferenceFound(List<WebUserPreference> webUserPreferences) {
		this.webUserPreferences = webUserPreferences;
	}

	public List<WebUserPreference> getWebUserPreferences()	{
		return webUserPreferences;
	}

}
