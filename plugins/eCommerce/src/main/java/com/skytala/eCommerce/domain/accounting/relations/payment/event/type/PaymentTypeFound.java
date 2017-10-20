package com.skytala.eCommerce.domain.accounting.relations.payment.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.type.PaymentType;
public class PaymentTypeFound implements Event{

	private List<PaymentType> paymentTypes;

	public PaymentTypeFound(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public List<PaymentType> getPaymentTypes()	{
		return paymentTypes;
	}

}
