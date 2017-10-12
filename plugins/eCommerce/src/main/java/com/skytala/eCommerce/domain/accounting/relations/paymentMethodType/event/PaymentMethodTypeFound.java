package com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.model.PaymentMethodType;
public class PaymentMethodTypeFound implements Event{

	private List<PaymentMethodType> paymentMethodTypes;

	public PaymentMethodTypeFound(List<PaymentMethodType> paymentMethodTypes) {
		this.paymentMethodTypes = paymentMethodTypes;
	}

	public List<PaymentMethodType> getPaymentMethodTypes()	{
		return paymentMethodTypes;
	}

}
