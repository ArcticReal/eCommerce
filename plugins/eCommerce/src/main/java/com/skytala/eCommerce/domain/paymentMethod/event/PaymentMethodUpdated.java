package com.skytala.eCommerce.domain.paymentMethod.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentMethod.model.PaymentMethod;
public class PaymentMethodUpdated implements Event{

	private boolean success;

	public PaymentMethodUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
