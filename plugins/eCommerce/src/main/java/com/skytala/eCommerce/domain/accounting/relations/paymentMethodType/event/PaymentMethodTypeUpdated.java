package com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.paymentMethodType.model.PaymentMethodType;
public class PaymentMethodTypeUpdated implements Event{

	private boolean success;

	public PaymentMethodTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
