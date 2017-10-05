package com.skytala.eCommerce.domain.glResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glResourceType.model.GlResourceType;
public class GlResourceTypeDeleted implements Event{

	private boolean success;

	public GlResourceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
