package com.skytala.eCommerce.domain.glAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.glAccount.model.GlAccount;
public class GlAccountDeleted implements Event{

	private boolean success;

	public GlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
