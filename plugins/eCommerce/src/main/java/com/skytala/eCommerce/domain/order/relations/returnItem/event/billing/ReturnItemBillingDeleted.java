package com.skytala.eCommerce.domain.order.relations.returnItem.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.billing.ReturnItemBilling;
public class ReturnItemBillingDeleted implements Event{

	private boolean success;

	public ReturnItemBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
