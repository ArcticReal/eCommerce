package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;
public class PayPalPaymentMethodFound implements Event{

	private List<PayPalPaymentMethod> payPalPaymentMethods;

	public PayPalPaymentMethodFound(List<PayPalPaymentMethod> payPalPaymentMethods) {
		this.payPalPaymentMethods = payPalPaymentMethods;
	}

	public List<PayPalPaymentMethod> getPayPalPaymentMethods()	{
		return payPalPaymentMethods;
	}

}
