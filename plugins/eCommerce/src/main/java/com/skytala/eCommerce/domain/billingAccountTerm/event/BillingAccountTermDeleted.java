package com.skytala.eCommerce.domain.billingAccountTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.billingAccountTerm.model.BillingAccountTerm;
public class BillingAccountTermDeleted implements Event{

	private boolean success;

	public BillingAccountTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
