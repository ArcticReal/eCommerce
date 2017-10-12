package com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.model.BillingAccountTermAttr;
public class BillingAccountTermAttrAdded implements Event{

	private BillingAccountTermAttr addedBillingAccountTermAttr;
	private boolean success;

	public BillingAccountTermAttrAdded(BillingAccountTermAttr addedBillingAccountTermAttr, boolean success){
		this.addedBillingAccountTermAttr = addedBillingAccountTermAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public BillingAccountTermAttr getAddedBillingAccountTermAttr() {
		return addedBillingAccountTermAttr;
	}

}
