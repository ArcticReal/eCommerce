package com.skytala.eCommerce.domain.accounting.relations.budget.event.paymentAllocation;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.budget.model.paymentAllocation.PaymentBudgetAllocation;
public class PaymentBudgetAllocationFound implements Event{

	private List<PaymentBudgetAllocation> paymentBudgetAllocations;

	public PaymentBudgetAllocationFound(List<PaymentBudgetAllocation> paymentBudgetAllocations) {
		this.paymentBudgetAllocations = paymentBudgetAllocations;
	}

	public List<PaymentBudgetAllocation> getPaymentBudgetAllocations()	{
		return paymentBudgetAllocations;
	}

}
