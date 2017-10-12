package com.skytala.eCommerce.domain.content.relations.webPreferenceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.webPreferenceType.model.WebPreferenceType;
public class WebPreferenceTypeDeleted implements Event{

	private boolean success;

	public WebPreferenceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
