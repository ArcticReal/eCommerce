package com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.model.GlAccountTypeDefault;
public class GlAccountTypeDefaultUpdated implements Event{

	private boolean success;

	public GlAccountTypeDefaultUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
