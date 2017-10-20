package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.term;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.term.BillingAccountTerm;
public class BillingAccountTermDeleted implements Event{

	private boolean success;

	public BillingAccountTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
