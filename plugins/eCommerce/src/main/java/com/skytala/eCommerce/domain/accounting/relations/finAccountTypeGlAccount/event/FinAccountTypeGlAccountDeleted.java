package com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.finAccountTypeGlAccount.model.FinAccountTypeGlAccount;
public class FinAccountTypeGlAccountDeleted implements Event{

	private boolean success;

	public FinAccountTypeGlAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
