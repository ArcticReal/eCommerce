package com.skytala.eCommerce.domain.billingAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.billingAccount.model.BillingAccount;
public class BillingAccountAdded implements Event{

	private BillingAccount addedBillingAccount;
	private boolean success;

	public BillingAccountAdded(BillingAccount addedBillingAccount, boolean success){
		this.addedBillingAccount = addedBillingAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BillingAccount getAddedBillingAccount() {
		return addedBillingAccount;
	}

}
