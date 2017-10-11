package com.skytala.eCommerce.domain.order.relations.returnItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemBilling.model.ReturnItemBilling;
public class ReturnItemBillingDeleted implements Event{

	private boolean success;

	public ReturnItemBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
