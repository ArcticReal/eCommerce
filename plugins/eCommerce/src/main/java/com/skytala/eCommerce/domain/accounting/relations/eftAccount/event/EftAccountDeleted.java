package com.skytala.eCommerce.domain.accounting.relations.eftAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
public class EftAccountDeleted implements Event{

	private boolean success;

	public EftAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
