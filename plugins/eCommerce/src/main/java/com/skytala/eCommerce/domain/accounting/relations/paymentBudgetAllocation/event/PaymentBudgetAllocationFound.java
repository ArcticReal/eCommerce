package com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentBudgetAllocation.model.PaymentBudgetAllocation;
public class PaymentBudgetAllocationFound implements Event{

	private List<PaymentBudgetAllocation> paymentBudgetAllocations;

	public PaymentBudgetAllocationFound(List<PaymentBudgetAllocation> paymentBudgetAllocations) {
		this.paymentBudgetAllocations = paymentBudgetAllocations;
	}

	public List<PaymentBudgetAllocation> getPaymentBudgetAllocations()	{
		return paymentBudgetAllocations;
	}

}
