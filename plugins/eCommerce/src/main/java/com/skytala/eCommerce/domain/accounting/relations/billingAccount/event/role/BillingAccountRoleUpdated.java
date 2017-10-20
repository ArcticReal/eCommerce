package com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role.BillingAccountRole;
public class BillingAccountRoleUpdated implements Event{

	private boolean success;

	public BillingAccountRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
