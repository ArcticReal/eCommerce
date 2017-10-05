package com.skytala.eCommerce.domain.paymentMethodType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.paymentMethodType.model.PaymentMethodType;
public class PaymentMethodTypeDeleted implements Event{

	private boolean success;

	public PaymentMethodTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
