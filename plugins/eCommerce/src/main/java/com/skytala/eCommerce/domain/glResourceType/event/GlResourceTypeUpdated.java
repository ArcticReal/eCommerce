package com.skytala.eCommerce.domain.glResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glResourceType.model.GlResourceType;
public class GlResourceTypeUpdated implements Event{

	private boolean success;

	public GlResourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
