package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.group.GlAccountGroup;
public class GlAccountGroupUpdated implements Event{

	private boolean success;

	public GlAccountGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
