package com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.paymentAllocation.PaymentBudgetAllocation;
public class PaymentBudgetAllocationAdded implements Event{

	private PaymentBudgetAllocation addedPaymentBudgetAllocation;
	private boolean success;

	public PaymentBudgetAllocationAdded(PaymentBudgetAllocation addedPaymentBudgetAllocation, boolean success){
		this.addedPaymentBudgetAllocation = addedPaymentBudgetAllocation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PaymentBudgetAllocation getAddedPaymentBudgetAllocation() {
		return addedPaymentBudgetAllocation;
	}

}
