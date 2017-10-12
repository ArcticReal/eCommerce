package com.skytala.eCommerce.domain.accounting.relations.glResourceType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glResourceType.model.GlResourceType;
public class GlResourceTypeDeleted implements Event{

	private boolean success;

	public GlResourceTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
