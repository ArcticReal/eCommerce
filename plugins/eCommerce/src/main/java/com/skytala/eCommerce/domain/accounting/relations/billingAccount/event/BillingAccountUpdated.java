package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;
public class BillingAccountUpdated implements Event{

	private boolean success;

	public BillingAccountUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
