package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;
public class PaymentBudgetAllocationUpdated implements Event{

	private boolean success;

	public PaymentBudgetAllocationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
