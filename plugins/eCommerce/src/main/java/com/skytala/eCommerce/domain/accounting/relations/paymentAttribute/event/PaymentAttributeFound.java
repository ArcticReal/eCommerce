package com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentAttribute.model.PaymentAttribute;
public class PaymentAttributeFound implements Event{

	private List<PaymentAttribute> paymentAttributes;

	public PaymentAttributeFound(List<PaymentAttribute> paymentAttributes) {
		this.paymentAttributes = paymentAttributes;
	}

	public List<PaymentAttribute> getPaymentAttributes()	{
		return paymentAttributes;
	}

}
