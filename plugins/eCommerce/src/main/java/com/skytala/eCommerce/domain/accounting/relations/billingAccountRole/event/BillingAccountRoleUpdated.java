package com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.model.BillingAccountRole;
public class BillingAccountRoleUpdated implements Event{

	private boolean success;

	public BillingAccountRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
