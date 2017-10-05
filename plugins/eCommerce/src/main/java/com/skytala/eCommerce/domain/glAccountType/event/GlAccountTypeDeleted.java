package com.skytala.eCommerce.domain.glAccountType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccountType.model.GlAccountType;
public class GlAccountTypeDeleted implements Event{

	private boolean success;

	public GlAccountTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
