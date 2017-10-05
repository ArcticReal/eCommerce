package com.skytala.eCommerce.domain.billingAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.billingAccount.model.BillingAccount;
public class BillingAccountDeleted implements Event{

	private boolean success;

	public BillingAccountDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
