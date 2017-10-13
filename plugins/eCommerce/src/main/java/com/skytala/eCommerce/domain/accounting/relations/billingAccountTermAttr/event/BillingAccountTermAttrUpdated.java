package com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.model.BillingAccountTermAttr;
public class BillingAccountTermAttrUpdated implements Event{

	private boolean success;

	public BillingAccountTermAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}