package com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.model.BillingAccountRole;
public class BillingAccountRoleDeleted implements Event{

	private boolean success;

	public BillingAccountRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
