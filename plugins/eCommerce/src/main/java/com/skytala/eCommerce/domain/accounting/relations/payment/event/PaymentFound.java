package com.skytala.eCommerce.domain.accounting.relations.payment.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
public class PaymentFound implements Event{

	private List<Payment> payments;

	public PaymentFound(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Payment> getPayments()	{
		return payments;
	}

}
