package com.skytala.eCommerce.domain.accounting.relations.payment.event.method;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.method.PaymentMethod;
public class PaymentMethodUpdated implements Event{

	private boolean success;

	public PaymentMethodUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
