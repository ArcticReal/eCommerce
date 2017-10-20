package com.skytala.eCommerce.domain.accounting.relations.payment.event.methodType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.methodType.PaymentMethodType;
public class PaymentMethodTypeFound implements Event{

	private List<PaymentMethodType> paymentMethodTypes;

	public PaymentMethodTypeFound(List<PaymentMethodType> paymentMethodTypes) {
		this.paymentMethodTypes = paymentMethodTypes;
	}

	public List<PaymentMethodType> getPaymentMethodTypes()	{
		return paymentMethodTypes;
	}

}
