package com.skytala.eCommerce.domain.accounting.relations.payment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.payment.model.Payment;
public class PaymentUpdated implements Event{

	private boolean success;

	public PaymentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
