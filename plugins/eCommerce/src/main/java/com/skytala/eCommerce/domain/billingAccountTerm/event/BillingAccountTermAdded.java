package com.skytala.eCommerce.domain.billingAccountTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.billingAccountTerm.model.BillingAccountTerm;
public class BillingAccountTermAdded implements Event{

	private BillingAccountTerm addedBillingAccountTerm;
	private boolean success;

	public BillingAccountTermAdded(BillingAccountTerm addedBillingAccountTerm, boolean success){
		this.addedBillingAccountTerm = addedBillingAccountTerm;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BillingAccountTerm getAddedBillingAccountTerm() {
		return addedBillingAccountTerm;
	}

}
