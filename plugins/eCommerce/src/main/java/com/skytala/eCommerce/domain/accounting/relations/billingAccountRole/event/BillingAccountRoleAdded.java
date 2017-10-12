package com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountRole.model.BillingAccountRole;
public class BillingAccountRoleAdded implements Event{

	private BillingAccountRole addedBillingAccountRole;
	private boolean success;

	public BillingAccountRoleAdded(BillingAccountRole addedBillingAccountRole, boolean success){
		this.addedBillingAccountRole = addedBillingAccountRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BillingAccountRole getAddedBillingAccountRole() {
		return addedBillingAccountRole;
	}

}
