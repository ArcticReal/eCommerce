package com.skytala.eCommerce.domain.accounting.relations.glResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
public class GlResourceTypeUpdated implements Event{

	private boolean success;

	public GlResourceTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
