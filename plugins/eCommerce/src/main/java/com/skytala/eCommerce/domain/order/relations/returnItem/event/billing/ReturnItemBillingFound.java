package com.skytala.eCommerce.domain.order.relations.returnItem.event.billing;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.billing.ReturnItemBilling;
public class ReturnItemBillingFound implements Event{

	private List<ReturnItemBilling> returnItemBillings;

	public ReturnItemBillingFound(List<ReturnItemBilling> returnItemBillings) {
		this.returnItemBillings = returnItemBillings;
	}

	public List<ReturnItemBilling> getReturnItemBillings()	{
		return returnItemBillings;
	}

}
