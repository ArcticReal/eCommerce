package com.skytala.eCommerce.domain.content.relations.webPreferenceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
public class WebPreferenceTypeAdded implements Event{

	private WebPreferenceType addedWebPreferenceType;
	private boolean success;

	public WebPreferenceTypeAdded(WebPreferenceType addedWebPreferenceType, boolean success){
		this.addedWebPreferenceType = addedWebPreferenceType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public WebPreferenceType getAddedWebPreferenceType() {
		return addedWebPreferenceType;
	}

}
