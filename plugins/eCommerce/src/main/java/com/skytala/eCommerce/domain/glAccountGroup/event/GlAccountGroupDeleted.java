package com.skytala.eCommerce.domain.glAccountGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountGroup.model.GlAccountGroup;
public class GlAccountGroupDeleted implements Event{

	private boolean success;

	public GlAccountGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
