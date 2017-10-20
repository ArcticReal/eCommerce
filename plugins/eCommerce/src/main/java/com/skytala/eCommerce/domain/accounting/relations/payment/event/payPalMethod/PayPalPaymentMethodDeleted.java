package com.skytala.eCommerce.domain.accounting.relations.payment.event.payPalMethod;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.payPalMethod.PayPalPaymentMethod;
public class PayPalPaymentMethodDeleted implements Event{

	private boolean success;

	public PayPalPaymentMethodDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}