package com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payPalPaymentMethod.model.PayPalPaymentMethod;
public class PayPalPaymentMethodUpdated implements Event{

	private boolean success;

	public PayPalPaymentMethodUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
