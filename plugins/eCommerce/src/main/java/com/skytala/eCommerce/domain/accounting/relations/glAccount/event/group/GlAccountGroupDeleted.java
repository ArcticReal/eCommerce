package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.group.GlAccountGroup;
public class GlAccountGroupDeleted implements Event{

	private boolean success;

	public GlAccountGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
