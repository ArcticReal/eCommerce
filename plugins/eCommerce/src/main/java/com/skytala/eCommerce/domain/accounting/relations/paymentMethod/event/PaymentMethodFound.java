package com.skytala.eCommerce.domain.accounting.relations.paymentMethod.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentMethod.model.PaymentMethod;
public class PaymentMethodFound implements Event{

	private List<PaymentMethod> paymentMethods;

	public PaymentMethodFound(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<PaymentMethod> getPaymentMethods()	{
		return paymentMethods;
	}

}
