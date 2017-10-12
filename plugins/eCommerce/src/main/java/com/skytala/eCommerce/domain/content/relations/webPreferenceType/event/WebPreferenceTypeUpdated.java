package com.skytala.eCommerce.domain.content.relations.webPreferenceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
public class WebPreferenceTypeUpdated implements Event{

	private boolean success;

	public WebPreferenceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
