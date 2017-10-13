package com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.model.GlAccountGroup;
public class GlAccountGroupUpdated implements Event{

	private boolean success;

	public GlAccountGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}