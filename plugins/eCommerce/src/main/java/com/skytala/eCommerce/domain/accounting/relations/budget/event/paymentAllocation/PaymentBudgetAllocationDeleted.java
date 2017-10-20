package com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.paymentAllocation.PaymentBudgetAllocation;
public class PaymentBudgetAllocationDeleted implements Event{

	private boolean success;

	public PaymentBudgetAllocationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
