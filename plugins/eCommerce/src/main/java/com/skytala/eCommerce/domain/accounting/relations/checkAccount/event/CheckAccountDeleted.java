package com.skytala.eCommerce.domain.accounting.relations.checkAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
public class CheckAccountDeleted implements Event{

	private boolean success;

	public CheckAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
