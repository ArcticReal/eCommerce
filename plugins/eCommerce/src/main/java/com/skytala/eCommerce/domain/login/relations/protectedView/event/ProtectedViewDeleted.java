package com.skytala.eCommerce.domain.login.relations.protectedView.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
public class ProtectedViewDeleted implements Event{

	private boolean success;

	public ProtectedViewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
