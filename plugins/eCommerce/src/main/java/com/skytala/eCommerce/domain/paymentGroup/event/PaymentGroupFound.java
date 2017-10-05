package com.skytala.eCommerce.domain.paymentGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentGroup.model.PaymentGroup;
public class PaymentGroupFound implements Event{

	private List<PaymentGroup> paymentGroups;

	public PaymentGroupFound(List<PaymentGroup> paymentGroups) {
		this.paymentGroups = paymentGroups;
	}

	public List<PaymentGroup> getPaymentGroups()	{
		return paymentGroups;
	}

}
