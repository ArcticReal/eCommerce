package com.skytala.eCommerce.domain.order.relations.returnItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemBilling.model.ReturnItemBilling;
public class ReturnItemBillingAdded implements Event{

	private ReturnItemBilling addedReturnItemBilling;
	private boolean success;

	public ReturnItemBillingAdded(ReturnItemBilling addedReturnItemBilling, boolean success){
		this.addedReturnItemBilling = addedReturnItemBilling;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ReturnItemBilling getAddedReturnItemBilling() {
		return addedReturnItemBilling;
	}

}
