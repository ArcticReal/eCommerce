package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
public class TarpittedLoginViewDeleted implements Event{

	private boolean success;

	public TarpittedLoginViewDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
