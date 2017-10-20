package com.skytala.eCommerce.domain.accounting.relations.payment.event.group;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.group.PaymentGroup;
public class PaymentGroupFound implements Event{

	private List<PaymentGroup> paymentGroups;

	public PaymentGroupFound(List<PaymentGroup> paymentGroups) {
		this.paymentGroups = paymentGroups;
	}

	public List<PaymentGroup> getPaymentGroups()	{
		return paymentGroups;
	}

}
