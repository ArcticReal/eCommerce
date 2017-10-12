package com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountTypeDefault.model.GlAccountTypeDefault;
public class GlAccountTypeDefaultDeleted implements Event{

	private boolean success;

	public GlAccountTypeDefaultDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
