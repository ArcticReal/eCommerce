package com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.payPalMethod.PayPalPaymentMethod;
public class PayPalPaymentMethodFound implements Event{

	private List<PayPalPaymentMethod> payPalPaymentMethods;

	public PayPalPaymentMethodFound(List<PayPalPaymentMethod> payPalPaymentMethods) {
		this.payPalPaymentMethods = payPalPaymentMethods;
	}

	public List<PayPalPaymentMethod> getPayPalPaymentMethods()	{
		return payPalPaymentMethods;
	}

}
