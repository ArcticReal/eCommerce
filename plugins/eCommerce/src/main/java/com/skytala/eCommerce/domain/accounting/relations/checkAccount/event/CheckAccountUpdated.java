package com.skytala.eCommerce.domain.accounting.relations.checkAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
public class CheckAccountUpdated implements Event{

	private boolean success;

	public CheckAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
