package com.skytala.eCommerce.domain.accounting.relations.payment.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.attribute.PaymentAttribute;
public class PaymentAttributeFound implements Event{

	private List<PaymentAttribute> paymentAttributes;

	public PaymentAttributeFound(List<PaymentAttribute> paymentAttributes) {
		this.paymentAttributes = paymentAttributes;
	}

	public List<PaymentAttribute> getPaymentAttributes()	{
		return paymentAttributes;
	}

}
