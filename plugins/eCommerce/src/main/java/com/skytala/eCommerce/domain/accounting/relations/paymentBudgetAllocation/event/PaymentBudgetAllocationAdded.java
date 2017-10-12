package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;
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
