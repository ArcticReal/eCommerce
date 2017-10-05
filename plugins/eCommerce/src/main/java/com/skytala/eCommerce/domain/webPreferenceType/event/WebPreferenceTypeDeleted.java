package com.skytala.eCommerce.domain.webPreferenceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.webPreferenceType.model.WebPreferenceType;
public class WebPreferenceTypeDeleted implements Event{

	private boolean success;

	public WebPreferenceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
